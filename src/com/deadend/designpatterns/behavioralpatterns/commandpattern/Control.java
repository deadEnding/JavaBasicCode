package com.deadend.designpatterns.behavioralpatterns.commandpattern;

/**
 * @author: deadend
 * @date: 2:10 PM 6/17/16
 * @version: 1.0
 * @description:
 */


public class Control {

    private Command commandOn;
    private Command commandOff;
    private Command commandChange;

    public Control(Command on, Command off, Command change) {
        commandOn = on;
        commandOff = off;
        commandChange = change;
    }

    public void turnOn() {
        commandOn.execute();
    }

    public void turnOff() {
        commandOff.execute();
    }

    public void changeChannel() {
        commandChange.execute();
    }
}
