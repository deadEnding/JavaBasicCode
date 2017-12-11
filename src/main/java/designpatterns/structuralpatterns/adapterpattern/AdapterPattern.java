package designpatterns.structuralpatterns.adapterpattern;

public class AdapterPattern {

    public static void main(String[] args) {
        Target target0 = new ClassAdapter();
        target0.sampleOperation1();

        Target target1 = new InstanceAdapter();
        target1.sampleOperation1();
    }
}
