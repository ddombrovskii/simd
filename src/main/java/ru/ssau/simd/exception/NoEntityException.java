package ru.ssau.simd.exception;

public class NoEntityException extends Exception {
    public NoEntityException(Long entityId) {
        super("Not found " + entityId.toString());
    }
}