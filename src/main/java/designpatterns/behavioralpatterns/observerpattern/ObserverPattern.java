package designpatterns.behavioralpatterns.observerpattern;

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);

        System.out.println("Second state change: 20");
        subject.setState(20);
    }
}
