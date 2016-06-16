package com.deadend.designpatterns.creationalpatterns.builderpattern;

/**
 * @author: deadend
 * @date: 10:25 AM 6/16/16
 * @version: 1.0
 * @description: ConcreteBuilder：实现Builder的接口以构造和装配该产品的各个部件，定义并明确它所创建的表示，并提供一个检索产品的接口。
 */


public class ManBuilder implements PersonBuilder {

    Person person;

    public ManBuilder() {
        this.person = new Man();
    };

    @Override
    public void buildHead() {
        person.setHead("男人的头");
    }

    @Override
    public void buildBody() {
        person.setBody("男人的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("男人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
