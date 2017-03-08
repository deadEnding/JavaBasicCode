package designpatterns.old.creationalpatterns.builderpattern;

/**
 * @author: java
 * @date: 10:37 AM 6/16/16
 * @version: 1.0
 * @description: 创建者模式，当创造一个对象需要很多步骤时适合使用建造者模式，而当只需调用一个方法就可以简单地创建整个对象时适合使用工厂模式
 *
 * @refer: http://blog.csdn.net/jason0539/article/details/44992733
 */


public class BuilderPatternDemo {

    public static void main(String[] args) {
        PersonDirector pd = new PersonDirector();
        Person man = pd.constructPerson(new ManBuilder());
        Person woman = pd.constructPerson(new WomanBuilder());
    }
}
