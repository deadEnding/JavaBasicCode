package designpatterns.old.behavioralpatterns.commandpattern;

/**
 * @author: java
 * @date: 11:08 AM 6/17/16
 * @version: 1.0
 * @description:
 *
 * @refer: http://blog.csdn.net/jason0539/article/details/45110355
 */


public class CommandPatternDemo {

    public static void main(String[] args) {
        Tv tv = new Tv();

        Command on = new CommandOn(tv);
        Command off = new CommandOff(tv);
        Command change = new CommandChange(tv, 2);

        Control control = new Control(on, off, change);

        control.turnOn();
        control.changeChannel();
        control.turnOff();
    }
}
