package org.example;

/**
 * Hello world!
 */
public class Client {

    public static void main(String[] args) {
        Isort isort = new SortedNumber(3, 1, 4, 6, 5, 8);
        isort.sort();
        Isort iSortStrLen = new SortedNumberToStringLength(31, 1, 41333, 613, 51234);
        iSortStrLen.sort();
        Isort iSortHash = new SortedHashCode(3, 1, 4, 6, 5, 8);
        iSortHash.sort();
    }
}
