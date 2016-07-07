package com.java.designpatterns.creationalpatterns.builderpattern;

/**
 * @author: java
 * @date: 10:35 AM 6/16/16
 * @version: 1.0
 * @description: Director：构造一个使用Builder接口的对象，指导构建过程。
 */


public class PersonDirector {

    public Person constructPerson(PersonBuilder pb) {
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.buildPerson();
    }
}
