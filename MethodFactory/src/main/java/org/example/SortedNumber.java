package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedNumber implements Isort {
    private List<Integer> list = new ArrayList<>();

    public SortedNumber(Integer... a) {
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
    }

    @Override
    public void sort() {
        list.sort(Comparator.naturalOrder());
        System.out.println(list.toString());
    }

}
