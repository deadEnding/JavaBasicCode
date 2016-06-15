package com.deadend.designpatterns.creationalpatterns.abstractfactorypattern;

/**
 * @author: deadend
 * @date: 4:25 PM 6/15/16
 * @version: 1.0
 * @description: 抽象工厂
 */


public abstract class AbstractFactory {

    abstract Color getColor(String type);
    abstract Shape getShape(String type);
}
