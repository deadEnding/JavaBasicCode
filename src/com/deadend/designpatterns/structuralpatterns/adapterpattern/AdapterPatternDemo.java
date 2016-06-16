package com.deadend.designpatterns.structuralpatterns.adapterpattern;

/**
 * @author: deadend
 * @date: 11:31 AM 6/16/16
 * @version: 1.0
 * @description: 适配器模式
 */


public class AdapterPatternDemo {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
