package designpatterns.old.creationalpatterns.abstractfactorypattern;

/**
 * @author: java
 * @date: 4:35 PM 6/15/16
 * @version: 1.0
 * @description: 工厂生成器
 */


public class FactoryProducer {

    public static AbstractFactory getFactory(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (type.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
