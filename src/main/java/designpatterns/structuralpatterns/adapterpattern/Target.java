package designpatterns.structuralpatterns.adapterpattern;

public interface Target {

    // 源类Adaptee也有的方法
    public void sampleOperation1();

    // 源类Adaptee没有的方法
    public void sampleOperation2();
}
