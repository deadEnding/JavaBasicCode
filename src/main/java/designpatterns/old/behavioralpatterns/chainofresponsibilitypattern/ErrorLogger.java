package designpatterns.old.behavioralpatterns.chainofresponsibilitypattern;

/**
 * @author: java
 * @date: 10:23 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console Logger: " + message);
    }
}
