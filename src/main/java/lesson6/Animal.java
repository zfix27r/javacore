package lesson6;

abstract class Animal {

    public static int counter;
    protected String name;
    protected int maxDistanceRun;
    protected int maxDistanceSwim;

    Animal(String name) {
        this.name = name;
        counter++;
    }

    boolean isSwim() {
        return maxDistanceSwim > 0;
    }

    boolean swim(int distance) {
        return distance <= maxDistanceSwim;
    }

    boolean run(int distance) {
        return distance <= maxDistanceRun;
    }
}
