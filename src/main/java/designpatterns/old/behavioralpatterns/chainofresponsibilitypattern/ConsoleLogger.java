package designpatterns.old.behavioralpatterns.chainofresponsibilitypattern;

/**
 * @author: java
 * @date: 10:20 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console Logger: " + message);
    }
}