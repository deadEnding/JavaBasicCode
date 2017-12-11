package designpatterns.behavioralpatterns.decoratorpattern;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        setBorderColor();
        shape.draw();
    }

    private void setBorderColor() {
        System.out.println("Set border color: red");
    }
}
