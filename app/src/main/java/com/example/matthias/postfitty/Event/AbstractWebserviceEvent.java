package com.example.matthias.postfitty.Event;

import com.example.matthias.postfitty.Utils.HttpStatus;

public abstract class AbstractWebserviceEvent<T> {

    protected T value;
    protected Object error;
    private final int httpCode;

    public AbstractWebserviceEvent(final Object value, final int httpCode) {
        if (HttpStatus.Companion.isSuccess(httpCode)) {
            this.value = (T) value;
        } else {
            this.error = value;
        }

        this.httpCode = httpCode;
    }

    public T getValue() {
        return value;
    }

    public int getCode() {
        return httpCode;
    }

    public boolean isResponsible(final Object responsible) {
        return isResponsible(responsible.getClass());
    }

    public boolean isSuccess() {
        return HttpStatus.Companion.isSuccess(httpCode);
    }
}

