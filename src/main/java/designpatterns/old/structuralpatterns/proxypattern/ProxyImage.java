package designpatterns.old.structuralpatterns.proxypattern;

/**
 * @author: java
 * @date: 9:58 AM 6/17/16
 * @version: 1.0
 * @description:
 */


public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
