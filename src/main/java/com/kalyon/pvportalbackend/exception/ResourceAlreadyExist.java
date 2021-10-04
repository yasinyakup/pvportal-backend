package com.kalyon.pvportalbackend.exception;

public class ResourceAlreadyExist extends RuntimeException{
    public ResourceAlreadyExist(String message){
        super(message);
    }
}
