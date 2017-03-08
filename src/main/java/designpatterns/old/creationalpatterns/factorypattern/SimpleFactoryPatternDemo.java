package designpatterns.old.creationalpatterns.factorypattern;

/**
 * @author: java
 * @date: 4:14 PM 6/15/16
 * @version: 1.0
 * @description:
 */


public class SimpleFactoryPatternDemo {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();

        Shape square = ShapeFactory.getShape("square");
        square.draw();
    }
}
