package lesson6;

public class Cat extends Animal {

    public static int counter = 0;

    Cat(String name) {
        super(name);

        counter++;
    }

    @Override
    public void run(int lengthBarrier) {
        int maxLength = 200;

        if (lengthBarrier <= maxLength) super.run(lengthBarrier);
        else System.out.println(name + " не может бегать больше " + maxLength + "м.");
    }

    @Override
    public void swim(int lengthBarrier) {
        System.out.println(name + " не умеет плавать.");
    }
}
