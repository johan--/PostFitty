package com.example.matthias.postfitty.Event;

public class OnNewPostCreatedEvent extends AbstractWebserviceEvent<Void> {

    public OnNewPostCreatedEvent(Void value, int httpCode) {
        super(value, httpCode);
    }
}
