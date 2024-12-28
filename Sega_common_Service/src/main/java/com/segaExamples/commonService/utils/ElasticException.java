package com.segaExamples.commonService.utils;

public class ElasticException extends Exception{

    private String errorCode;
    public ElasticException(String errorMessage){
        super(errorMessage);
    }
}
