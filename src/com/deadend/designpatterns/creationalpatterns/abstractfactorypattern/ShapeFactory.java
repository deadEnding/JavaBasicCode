package com.deadend.designpatterns.creationalpatterns.abstractfactorypattern;

/**
 * @author: deadend
 * @date: 4:27 PM 6/15/16
 * @version: 1.0
 * @description: 形状工厂
 */


public class ShapeFactory extends AbstractFactory {

    @Override
    Color getColor(String type) {
        return null;
    }

    @Override
    Shape getShape(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (type.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
