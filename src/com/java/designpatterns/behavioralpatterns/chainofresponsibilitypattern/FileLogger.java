package com.java.designpatterns.behavioralpatterns.chainofresponsibilitypattern;

/**
 * @author: java
 * @date: 10:25 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File Logger: " + message);
    }
}
