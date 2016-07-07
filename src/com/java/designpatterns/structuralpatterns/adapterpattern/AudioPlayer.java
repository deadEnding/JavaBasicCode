package com.java.designpatterns.structuralpatterns.adapterpattern;

/**
 * @author: java
 * @date: 11:25 AM 6/16/16
 * @version: 1.0
 * @description: 通过MediaAdapter适配器使原本只支持mp3格式的AudioPlayer拥有了AdvancedMediaPlayer播放vlc和mp4的功能。
 */


public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // 播放mp3音乐文件的内置支持
        if (audioType.equalsIgnoreCase("MP3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
        // 使用mediaAdapter提供播放其他文件格式的支持
        else if (audioType.equalsIgnoreCase("VLC") || audioType.equalsIgnoreCase("MP4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported.");
        }
    }
}
