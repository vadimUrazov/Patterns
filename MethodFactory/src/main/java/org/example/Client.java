package org.example;

/**
 * Hello world!
 */
public class Client {

    public static void main(String[] args) {
        Isort isort = new SortedBubble(3, 1, 4, 6, 5, 8);
        isort.sort();
        Isort iSortStrLen = new SortedRadix(31, 1, 41333, 613, 51234);
        iSortStrLen.sort();
        Isort iSortHash = new SortedQuick(3, 1, 4, 6, 5, 8);
        iSortHash.sort();
    }
}
