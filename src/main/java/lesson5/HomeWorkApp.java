package lesson5;

public class HomeWorkApp {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Sergey", "Zabelin", "Student", "sergey@geekbrains.ru", "89990001122", 10_000, 50);
        employees[1] = new Employee("Aleksei", "Zabelin", "Student", "aleksei@geekbrains.ru", "89992223312", 20_000, 19);
        employees[2] = new Employee("Anna", "Zabelina", "Star", "anna@geekbrains.ru", "89990990088", 30_000, 50);
        employees[3] = new Employee("Violeta", "Zabelina", "Developer", "vi@geekbrains.ru", "89990021232", 40_000, 35);
        employees[4] = new Employee("Tatiana", "Zabelina", "Student", "tatiana@geekbrains.ru", "89990231223", 800_000, 20);

        for (Employee e : employees
        ) {
            if (e.getAge() > 39)
                System.out.println(e);
        }
    }
}
