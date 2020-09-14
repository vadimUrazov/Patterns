package org.example;

import java.util.ArrayList;
import java.util.List;

public class SortedHashCode implements Isort {
    private List<Integer> list = new ArrayList<>();

    public SortedHashCode(Integer... a) {
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }

    }

    @Override
    public void sortNatural() {

    }

    @Override
    public void sortToStrLen() {

    }

    @Override
    public void sortHash() {
        list.sort((o1, o2) -> Integer.compare(o1.hashCode(), o2.hashCode()));
        System.out.println(list.toString());
    }
}
