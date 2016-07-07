package com.java.designpatterns.behavioralpatterns.commandpattern;

/**
 * @author: java
 * @date: 2:06 PM 6/17/16
 * @version: 1.0
 * @description: 开机命令
 */


public class CommandOn implements Command {

    private Tv tv;

    public CommandOn(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.tunrOn();
    }
}
