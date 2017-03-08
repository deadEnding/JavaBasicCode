package designpatterns.old.creationalpatterns.abstractfactorypattern;

/**
 * @author: java
 * @date: 4:25 PM 6/15/16
 * @version: 1.0
 * @description: 抽象工厂
 */


public abstract class AbstractFactory {

    abstract Color getColor(String type);
    abstract Shape getShape(String type);
}
