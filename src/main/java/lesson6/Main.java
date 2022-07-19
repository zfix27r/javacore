package lesson6;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String msgRun = "%s %sпробежал(а) %dм.";
        String msgSwim = "%s %sпроплыл(а) %dм.";


        Random random = new Random();

        Animal[] animals = new Animal[4];
        animals[0] = new Dog("Барсик");
        animals[1] = new Dog("Шарик");
        animals[2] = new Cat("Автобус");
        animals[3] = new Cat("Киса");

        printAnimalInfo();

        for (Animal animal : animals
        ) {
            int runDistance = random.nextInt(1000);
            int swimDistance = random.nextInt(20);

            printAnimalAction(msgRun, animal, runDistance);

            if (animal.isSwim()) printAnimalAction(msgSwim, animal, swimDistance);
            else printAnimalCantSwim(animal.name);

            System.out.println();
        }
    }

    private static void printAnimalInfo() {
        System.out.println("Животных: " + Animal.counter);
        System.out.println("Собак: " + Dog.counter);
        System.out.println("Кошек: " + Cat.counter);
        System.out.println();
    }

    private static void printAnimalAction(String msg, Animal animal, int distance) {
        System.out.printf(
                msg,
                animal.name,
                animal.swim(distance) ? "" : "не ",
                distance
        );
        System.out.println();
    }

    private static void printAnimalCantSwim(String name) {
        String msg = "%s не умеет плаавать.";
        System.out.printf(msg, name);
        System.out.println();
    }
}
