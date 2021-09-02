package com.jcoder.spring.data.jpa.tutorial.exception;

public class StudentNotFoundException extends  Exception{

    public StudentNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
    public StudentNotFoundException(){
        super();
    }

}
