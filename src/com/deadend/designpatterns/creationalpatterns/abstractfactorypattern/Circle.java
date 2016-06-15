package com.deadend.designpatterns.creationalpatterns.abstractfactorypattern;

/**
 * @author: deadend
 * @date: 4:09 PM 6/15/16
 * @version: 1.0
 * @description:
 */


public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing circle ...");
    };
}