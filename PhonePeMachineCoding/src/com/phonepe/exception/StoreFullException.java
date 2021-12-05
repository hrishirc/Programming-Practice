package com.phonepe.exception;

import java.text.MessageFormat;

public class StoreFullException extends Exception {
    private int availableSize;
    private int contentSize;

    public StoreFullException(int availableSize, int contentSize) {
        super(MessageFormat.format("Available size in store is {0} bytes while content size is {1} bytes", availableSize, contentSize));
        this.availableSize = availableSize;
        this.contentSize = contentSize;
    }
}
