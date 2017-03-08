package designpatterns.old.behavioralpatterns.chainofresponsibilitypattern;

/**
 * @author: java
 * @date: 10:13 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public abstract class AbstractLogger {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    // 责任链中的下一元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}
