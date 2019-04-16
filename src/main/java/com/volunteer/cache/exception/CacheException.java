package com.volunteer.cache.exception;

public class CacheException extends RuntimeException {
    private static final long serialVersionUID = -9013602095254607503L;

    public CacheException(){
        super("cache operator exception");
    }

    public CacheException(Exception e){
        super("cache operator exception",e);
    }
}
