package lesson6;

public class Dog extends Animal {

    public static int counter = 0;

    Dog(String name) {
        super(name);

        counter++;
    }

    @Override
    public void run(int lengthBarrier) {
        int maxLength = 500;

        if (lengthBarrier <= maxLength) super.run(lengthBarrier);
        else System.out.println(name + " не может бегать больше " + maxLength + "м.");
    }

    @Override
    public void swim(int lengthBarrier) {
        int maxLength = 10;

        if (lengthBarrier <= maxLength) super.swim(lengthBarrier);
        else System.out.println(name + " проплывет не больше " + maxLength + "м.");
    }
}
