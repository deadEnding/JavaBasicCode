package designpatterns.old.creationalpatterns.builderpattern;

/**
 * @author: java
 * @date: 10:22 AM 6/16/16
 * @version: 1.0
 * @description: Builder：为创建一个产品对象的各个部件指定抽象接口。
 */


public interface PersonBuilder {
    void buildHead();
    void buildBody();
    void buildFoot();
    Person buildPerson();
}
