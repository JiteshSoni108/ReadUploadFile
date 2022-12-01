package com.fileread.filereaddemo.exception;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String msg) {
        super(msg);
    }
}