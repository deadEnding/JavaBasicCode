package com.deadend.designpatterns.behavioralpatterns.chainofresponsibilitypattern;

/**
 * @author: deadend
 * @date: 10:30 AM 6/17/16
 * @version: 1.0
 * @description: 职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递，所以职责链将请求的发送者和请求的处理者解耦了
 */


public class ChainofResponsibilityPatternDemo {

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "info message");
        loggerChain.logMessage(AbstractLogger.DEBUG, "debug message");
        loggerChain.logMessage(AbstractLogger.ERROR, "error message");
    }
}
