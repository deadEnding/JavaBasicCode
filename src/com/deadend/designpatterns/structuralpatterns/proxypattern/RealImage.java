package com.deadend.designpatterns.structuralpatterns.proxypattern;

/**
 * @author: deadend
 * @date: 9:56 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName + " ...");
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName + " ...");
    }
}
