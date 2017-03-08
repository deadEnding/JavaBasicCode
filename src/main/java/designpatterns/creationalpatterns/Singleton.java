package designpatterns.creationalpatterns;

/**
 * @author: deadend
 * @date: 8:00 PM 1/22/17
 * @version: 1.0
 * @description:
 */


class Singleton1 {
    private static Singleton1 instance;

    private Singleton1(){}

    public Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {}

    public synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

class Singleton3 {
    private static Singleton3 instance = new Singleton3();

    private Singleton3() {}

    public Singleton3 getInstance() {
        return instance;
    }
}

class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {}

    public Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

class Singleton5 {
    private static class SingletonHolder {
        private static final Singleton5 instance = new Singleton5();
    }

    private Singleton5() {};

    public Singleton5 getInstance() {
        return SingletonHolder.instance;
    }
}

enum Singleton6 {
    INSTANCE;
    public void whateverMethod() {}
}
