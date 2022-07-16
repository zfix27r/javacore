package lesson6;

public class Dog extends Animal {

    public static int counter = 0;

    Dog(String name) {
        super(name);
        maxDistanceRun = 500;
        maxDistanceSwim = 10;
        counter++;
    }
}
