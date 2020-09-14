package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

 public class SortedBubble implements Isort {
    private int[] list;

    public SortedBubble(Integer... a) {
        list=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            list[i]=a[i];
        }

    }

    @Override
    public void sort() {
        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.length-1; i++) {
                if(list[i] > list[i+1]){
                    isSorted = false;

                    buf = list[i];
                    list[i] = list[i+1];
                    list[i+1] = buf;
                }
            }
        }
        for(int i: list){
            System.out.print(" "+i);
        }
    }

}
