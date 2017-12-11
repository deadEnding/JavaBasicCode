package designpatterns.structuralpatterns.adapterpattern;

public class InstanceAdapter implements Target {

    private Adaptee adaptee;

    @Override
    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }

    @Override
    public void sampleOperation2() {

    }
}
