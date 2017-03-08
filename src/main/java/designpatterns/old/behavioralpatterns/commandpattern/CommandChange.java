package designpatterns.old.behavioralpatterns.commandpattern;

/**
 * @author: java
 * @date: 2:08 PM 6/17/16
 * @version: 1.0
 * @description: 换台
 */


public class CommandChange implements Command {

    private Tv tv;
    private int channel;

    public CommandChange(Tv tv, int channel) {
        this.tv = tv;
        this.channel = channel;
    }

    @Override
    public void execute() {
        tv.changeChannel(channel);
    }
}
