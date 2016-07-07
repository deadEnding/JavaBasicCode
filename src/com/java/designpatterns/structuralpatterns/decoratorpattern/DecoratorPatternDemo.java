package com.java.designpatterns.structuralpatterns.decoratorpattern;

/**
 * @author: java
 * @date: 9:31 AM 6/17/16
 * @version: 1.0
 * @description: 装饰器模式，允许向一个现有的对象添加新的功能，同时又不改变其结构。
 */


public class DecoratorPatternDemo {

    public static void main(String[] args) {
        Shape circle = new Circle();
        circle.draw();

        Shape redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        redRectangle.draw();
    }
}
