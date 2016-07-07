package com.java.designpatterns.structuralpatterns.decoratorpattern;

/**
 * @author: java
 * @date: 9:24 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing circle ...");
    }
}
