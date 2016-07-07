package com.java.designpatterns.structuralpatterns.proxypattern;

/**
 * @author: java
 * @date: 10:00 AM 6/17/16
 * @version: 1.0
 * @description: 代理模式，一个类代表另一个类的功能
 */


public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("pic.jpg");

        image.display();
    }
}