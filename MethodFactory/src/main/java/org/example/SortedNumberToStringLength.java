package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedNumberToStringLength implements Isort{
    private List<Integer> list=new ArrayList<>();
    public SortedNumberToStringLength(Integer...a) {
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
    }

    @Override
    public void sortNatural() {

    }

    @Override
    public void sortToStrLen() {
list.sort((o1, o2) ->Integer.valueOf(o1.toString().length()).
        compareTo(Integer.valueOf(o2.toString().length())));
        System.out.println(list.toString());
    }

    @Override
    public void sortHash() {

    }
}
