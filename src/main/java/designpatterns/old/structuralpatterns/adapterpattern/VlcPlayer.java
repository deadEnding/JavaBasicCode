package designpatterns.old.structuralpatterns.adapterpattern;

/**
 * @author: java
 * @date: 11:16 AM 6/16/16
 * @version: 1.0
 * @description:
 */


public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
