package designpatterns.old.structuralpatterns.decoratorpattern;

/**
 * @author: java
 * @date: 9:23 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing rectangle ...");
    }
}
