package lesson6;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        Animal[] animals = new Animal[4];
        animals[0] = new Dog("Барсик");
        animals[1] = new Dog("Шарик");
        animals[2] = new Cat("Автобус");
        animals[3] = new Cat("Киса");

        System.out.println("Животных: " + Animal.counter);
        System.out.println("Собак: " + Dog.counter);
        System.out.println("Кошек: " + Cat.counter);

        for (Animal animal : animals
        ) {
            System.out.println();
            animal.run(random.nextInt(1000));
            animal.swim(random.nextInt(20));
        }
    }
}
