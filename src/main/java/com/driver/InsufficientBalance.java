package com.driver;
public class InsufficientBalance extends RuntimeException{

    public InsufficientBalance() {
        super("Insufficient Balance");
    }

}
