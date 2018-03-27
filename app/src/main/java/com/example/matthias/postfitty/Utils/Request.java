package com.example.matthias.postfitty.Utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;


import com.example.matthias.postfitty.Api.Webservice;
import com.example.matthias.postfitty.Event.AbstractWebserviceEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Request<T> {
    final Call<T> call;
    private final Object resolvingLock = new Object();
    private final LinkedBlockingQueue<Request> waitingRequests;
    private final Webservice webservice;

    private ResponseBody errorBody;
    private T value;
    private int code;
    private boolean isExecuted;
    private boolean isResolvingError;


    protected Request(final Call<T> call, final LinkedBlockingQueue<Request> waitingRequests, final Webservice webservice) {
        this.call = call;
        this.waitingRequests = waitingRequests;
        this.webservice = webservice;
        this.code = HttpStatus.Companion.getHTTP_TEAPOT();
    }

    protected void executeInBackground() {

        final Response<T> response;

        synchronized (resolvingLock) {
            if (isResolvingError) {
                try {
                    waitingRequests.put(this);
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return;
            }
        }
        try {
            final Call<T> toExecute = call.isExecuted() || call.isCanceled() ? call.clone() : call;
            response = toExecute.execute();
            code = response.code();
            if (!response.isSuccessful()) {
                if (response.code() == HttpStatus.Companion.getHTTP_UNAUTHORIZED() || response.code() == HttpStatus.Companion.getHTTP_FORBIDDEN()) {
                    synchronized (resolvingLock) {
                        try {
                            waitingRequests.put(this);
                        } catch (final InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        if (!isResolvingError) {
                            isResolvingError = true;
                        }
                    }
                    return;
                }
                errorBody = response.errorBody();
            } else {
                value = response.body();
                if (value != null) {
                    value = postProcess(value);
                }
                code = response.code();
            }
        } catch (final IOException e) {
            if (e instanceof UnknownHostException) {
                code = HttpStatus.Companion.getHTTP_UNAVAILABLE();
            } else {
                code = HttpStatus.Companion.getHTTP_INTERNAL_ERROR();
            }
        } catch (final IllegalStateException e) {
            throw new IllegalStateException("Problems with call " + call.request().url().toString(), e);
        }

        isExecuted = true;
    }

    protected void onExecuted() {
        if (!isExecuted) {
            return;
        }
        final AbstractWebserviceEvent event =
                errorBody == null ? newEvent(value, code) : newErrorEvent(errorBody, code);
        if (event != null) {
            EventBus.getDefault().post(event);
        }
    }

    protected AbstractWebserviceEvent newEvent(final T value, final int code) {
        return null;
    }

    protected AbstractWebserviceEvent newErrorEvent(final ResponseBody errorBody, final int code) {
        return newEvent(null, code);
    }

    protected T postProcess(@NonNull final T value) {
        return value;
    }

    public void execute() {
        new WebserviceTask(this).execute();
    }

    private static class WebserviceTask extends AsyncTask<Void, Void, Void> {

        private final Request request;

        WebserviceTask(final Request request) {
            this.request = request;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            this.request.executeInBackground();
            return null;
        }

        @Override
        protected void onPostExecute(final Void aVoid) {
            this.request.onExecuted();
        }
    }
}
