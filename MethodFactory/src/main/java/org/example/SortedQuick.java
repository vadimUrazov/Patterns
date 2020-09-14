package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedQuick implements Isort {
    private int[] list;

    public SortedQuick(Integer... a) {
        list = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            list[i] = a[i];
        }

    }

    @Override
    public void sort() {

        quickSort(list, 0, list.length - 1);
        for (int i : list) {
            System.out.print(" " + i);
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

}
