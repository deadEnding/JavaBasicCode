package serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SerializeDemo {

    static class Person implements Serializable {
        String name;
        int age;

        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Person[name=%s,age=%d]", name, age);
        }
    }

    public static List<Person> people = new ArrayList<>();

    public static void create() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            people.add(new Person("deadend" + random.nextInt(100), random.nextInt(10000)));
        }
    }

    public static void kryoSerialize() throws FileNotFoundException {
        String path = "/tmp/test/kryofile.bin";

        Kryo kryo = new Kryo();

        Output output = new Output(new FileOutputStream(path));
        for (Person p : people) {
            kryo.writeObject(output, p);
        }
        output.close();

        Input input = new Input(new FileInputStream(path));
        while (!input.eof()) {
            Person p = kryo.readObject(input, Person.class);
            System.out.println(p);
        }
        input.close();
    }

    public static void javaSerialize() throws IOException, ClassNotFoundException {
        String path = "/tmp/test/javafile.bin";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        for (Person p : people) {
            oos.writeObject(p);
        }
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Person person = null;
        try {
            while ((person = (Person) ois.readObject()) != null) {
                System.out.println(person);
            }
        } catch (EOFException e) {}
        ois.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        create();

        kryoSerialize();
        javaSerialize();
    }
}
