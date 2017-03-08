package designpatterns.old.structuralpatterns.adapterpattern;

/**
 * @author: java
 * @date: 11:15 AM 6/16/16
 * @version: 1.0
 * @description:
 */


public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}
