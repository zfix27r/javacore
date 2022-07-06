package lesson3;


import java.util.Arrays;
import java.util.Random;

public class HomeWorkApp {

    public static void main(String[] args) {

        oneArray();
        twoArray();
        threeArray();
        fourArray();
        initArray(10, 2);
        fiveArray();

        int[] arr = {10, 9, 5, 42, 52, 3, 9, 4, 5, 6, 6};
        isFindBalance(arr);
        shiftArray(arr, -4);
    }

    private static void oneArray() {
        int[] arr = {0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
    }

    private static void twoArray() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

    private static void threeArray() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
    }

    private static void fourArray() {
        int[][] arr = new int[10][10];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if ((j + i) % 2 == 0) {
                    arr[i][j] = 1;
                }
            }
        }
    }

    private static int[] initArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    private static void fiveArray() {
        Random random = new Random();
        int[] arr = new int[30];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        int min = arr[0];
        int max = arr[0];

        for (int j : arr) {
            if (j > max) {
                max = j;
            }

            if (j < min) {
                min = j;
            }
        }
    }

    private static Boolean isFindBalance(int[] arr) {
        if (arr.length > 2) {
            int border = 2;

            do {
                int sum1 = 0;
                int sum2 = 0;

                for (int j = 0; j < arr.length; j++) {
                    if (j < border) {
                        sum1 += arr[j];
                    } else {
                        sum2 += arr[j];
                    }
                }

                if (sum1 == sum2) {
                    return true;
                }
            } while (++border != arr.length - 1);
        }

        return false;
    }

    private static void shiftArray(int[] arr, int n) {
        int shift = n;
        if (n * -1 > arr.length || n > arr.length) {
            shift = n % arr.length;
        }

        if (shift == arr.length || shift * -1 == arr.length || shift == 0) {
            return;
        }

        if (shift < 0) {
            shift += arr.length;
        }

        for (int i = 0; i < shift; i++) {
            int savedValue = arr[0];

            for (int j = 0; j < arr.length; j++) {
                if (j == arr.length - 1) {
                    arr[0] = arr[arr.length - 1];
                }

                int value = arr[j];
                arr[j] = savedValue;
                savedValue = value;
            }
        }
    }
}
