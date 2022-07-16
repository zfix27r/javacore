package lesson6;

public class Animal {

    public static int counter;

    protected String name;

    Animal(String name) {
        this.name = name;

        counter++;
    }

    public void swim(int lengthBarrier) {
        System.out.println(name + " проплыл " + lengthBarrier + "м.");
    }

    public void run(int lengthBarrier) {
        System.out.println(name + " пробежал " + lengthBarrier + "м.");
    }
}
