package designpatterns.old.creationalpatterns.factorypattern;

/**
 * @author: java
 * @date: 3:26 PM 6/15/16
 * @version: 1.0
 * @description: 简单工厂
 */


public class ShapeFactory {

    public static Shape getShape(String type) {
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
