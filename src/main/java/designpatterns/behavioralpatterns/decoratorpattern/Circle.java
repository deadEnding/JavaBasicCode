package designpatterns.behavioralpatterns.decoratorpattern;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Draw circle ...");
    }
}
