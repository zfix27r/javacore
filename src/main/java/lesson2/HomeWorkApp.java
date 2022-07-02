package lesson2;

public class HomeWorkApp {
    public static void main(String[] args) {

        System.out.println(isSumRange(22, -5));

        printNumberState(0);

        System.out.println(isNumberNegative(999));

        printStringSomeTimes("напечатай меня", 10);

        System.out.println(isLeapYear(1600));
    }

    private static Boolean isSumRange(int a, int b) {
        int sum = a + b;
        return sum > 9 & sum < 21;
    }

    private static void printNumberState(int i) {
        System.out.print("число " + i + " ");

        if (i < 0) {
            System.out.println("отрицательное");
        } else {
            System.out.println("положительное");
        }
    }

    private static Boolean isNumberNegative(int i) {
        return i < 0;
    }

    private static void printStringSomeTimes(String inputString, int i) {
        while (i > 0) {
            System.out.println(inputString);
            i--;
        }
    }

    private static Boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
