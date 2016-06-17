package com.deadend.designpatterns.behavioralpatterns.commandpattern;

/**
 * @author: deadend
 * @date: 2:07 PM 6/17/16
 * @version: 1.0
 * @description:
 */


public class CommandOff implements Command {

    private Tv tv;

    public CommandOff(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}
