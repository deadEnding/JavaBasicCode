package designpatterns.behavioralpatterns.chainofresponsibilitypattern;

public class ChainOfResponsibilityPattern {

    public static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMsg(AbstractLogger.INFO, "info 1");
        loggerChain.logMsg(AbstractLogger.DEBUG, "info 2");
        loggerChain.logMsg(AbstractLogger.ERROR, "info 3");
    }
}
