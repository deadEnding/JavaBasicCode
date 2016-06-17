package com.deadend.designpatterns.structuralpatterns.decoratorpattern;

/**
 * @author: deadend
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
