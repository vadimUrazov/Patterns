package org.example;

import java.util.*;

 public class SortedRadix implements Isort {
    private int[] list ;

    public SortedRadix(Integer... a) {
        list=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            list[i]=a[i];

        }

    }

    @Override
    public void sort() {
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            // The array to put the partially sorted array into
            int[] tmp = new int[list.length];
            // The number of 0s
            int j = 0;

            // Move the 0s to the new array, and the 1s to the old one
            for (int i = 0; i < list.length; i++) {
                // If there is a 1 in the bit we are testing, the number will be negative
                boolean move = list[i] << shift >= 0;

                // If this is the last bit, negative numbers are actually lower
                if (shift == 0 ? !move : move) {
                    tmp[j] = list[i];
                    j++;
                } else {
                    // It's a 1, so stick it in the old array for now
                    list[i - j] = list[i];
                }
            }

            // Copy over the 1s from the old array
            for (int i = j; i < tmp.length; i++) {
                tmp[i] = list[i - j];
            }

            // And now the tmp array gets switched for another round of sorting
            list = tmp;
        }
        for(int i: list){
            System.out.print(" "+i);
        }
    }
}
