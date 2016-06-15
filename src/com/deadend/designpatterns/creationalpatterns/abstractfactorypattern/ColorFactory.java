package com.deadend.designpatterns.creationalpatterns.abstractfactorypattern;

/**
 * @author: deadend
 * @date: 4:31 PM 6/15/16
 * @version: 1.0
 * @description: 颜色工厂
 */


public class ColorFactory extends AbstractFactory{

    @Override
    Color getColor(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (type.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    Shape getShape(String type) {
        return null;
    }
}
