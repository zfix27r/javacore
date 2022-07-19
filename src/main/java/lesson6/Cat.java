package lesson6;

public class Cat extends Animal {

    public static int counter = 0;

    Cat(String name) {
        super(name);
        maxDistanceRun = 200;
        maxDistanceSwim = 0;
        counter++;
    }
}
