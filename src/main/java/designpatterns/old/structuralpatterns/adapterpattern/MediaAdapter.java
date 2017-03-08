package designpatterns.old.structuralpatterns.adapterpattern;

/**
 * @author: java
 * @date: 11:18 AM 6/16/16
 * @version: 1.0
 * @description:
 */


public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("VLC")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("MP4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("VLC")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("MP4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
