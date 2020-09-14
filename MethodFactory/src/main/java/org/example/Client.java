package org.example;

/**
 * Hello world!
 */
public class Client {

    public static void main(String[] args) {
        Isort isort = new SortedNumber(3, 1, 4, 6, 5, 8);
        isort.sortNatural();
        Isort iSortStrLen = new SortedNumberToStringLength(31, 1, 41333, 613, 51234);
        iSortStrLen.sortToStrLen();
        Isort iSortHash = new SortedHashCode(3, 1, 4, 6, 5, 8);
        iSortHash.sortHash();
    }
}
