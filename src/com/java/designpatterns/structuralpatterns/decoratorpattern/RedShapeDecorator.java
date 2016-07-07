package com.java.designpatterns.structuralpatterns.decoratorpattern;

/**
 * @author: java
 * @date: 9:27 AM 6/17/16
 * @version: 1.0
 * @description: 使用 RedShapeDecorator 来装饰 Shape 对象。
 */


public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border color: red");
    }
}
