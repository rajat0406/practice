package com.extramarks.journeybuilder.exception;

public enum ExceptionType {
    NOT_FOUND("not.found"),
    DUPLICATE("duplicate"),
    EXCEPTION("exception");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}