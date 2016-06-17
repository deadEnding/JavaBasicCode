package com.deadend.designpatterns.behavioralpatterns.commandpattern;

/**
 * @author: deadend
 * @date: 2:03 PM 6/17/16
 * @version: 1.0
 * @description: 命令的接受者Receiver
 */


public class Tv {
    public int currentChannel = 0;

    public void tunrOn() {
        System.out.println("The television is on.");
    }

    public void turnOff() {
        System.out.println("The television is off.");
    }

    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("Now TV channel is " + channel);
    }
}
