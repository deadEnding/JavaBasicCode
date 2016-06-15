package com.deadend.designpatterns.creationalpatterns.abstractfactorypattern;

/**
 * @author: deadend
 * @date: 4:37 PM 6/15/16
 * @version: 1.0
 * @description:
 */


public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();
        Shape square = shapeFactory.getShape("SQUARE");
        square.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        Color red = colorFactory.getColor("RED");
        red.fill();
        Color blue = colorFactory.getColor("BLUE");
        blue.fill();
    }
}
