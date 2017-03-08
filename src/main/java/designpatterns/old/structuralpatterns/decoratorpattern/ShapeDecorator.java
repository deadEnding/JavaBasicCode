package designpatterns.old.structuralpatterns.decoratorpattern;

/**
 * @author: java
 * @date: 9:25 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public abstract class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
