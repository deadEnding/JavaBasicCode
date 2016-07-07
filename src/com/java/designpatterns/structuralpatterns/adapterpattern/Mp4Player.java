package com.java.designpatterns.structuralpatterns.adapterpattern;

/**
 * @author: java
 * @date: 11:17 AM 6/16/16
 * @version: 1.0
 * @description:
 */


public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
