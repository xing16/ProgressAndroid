package com.xing.progressandroid;

import java.util.Arrays;

public class Main {

    public static void main(String[] a) {
        Main main = new Main();
        main.sortSelect(new int[]{3, 1, 67, 23, 12, 100, 1, 21});
    }

    private void sortSelect(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
