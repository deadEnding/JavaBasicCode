package com.java.designpatterns.creationalpatterns.builderpattern;

/**
 * @author: java
 * @date: 10:23 AM 6/16/16
 * @version: 1.0
 * @description: Product：表示被构造的复杂对象。ConcreteBuilder创建该产品的内部表示并定义它的装配过程，包含定义组成部件的类，包括将这些部件装配成最终产品的接口。
 */


abstract public class Person {

    private String head;
    private String body;
    private String foot;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }
}

class Man extends Person {

    public Man() {
        System.out.println("开始建造男人...");
    };
}

class Woman extends Person {

    public Woman() {
        System.out.println("开始建造女人...");
    }
}


