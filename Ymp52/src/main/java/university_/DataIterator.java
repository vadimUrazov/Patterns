package university_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataIterator implements Iterator<Integer> {
    private int index;
    private Data setOfGroups;

    public DataIterator(Data setOfGroups) {
        index = -1;
        this.setOfGroups = setOfGroups;
    }

    @Override
    public boolean hasNext() {
        int size = 0;
        for (int i = 0; i < setOfGroups.length(); i++) {
            size += setOfGroups.getSet()[i].length();

        }
        return (index + 1) < size;
    }

    @Override
    public Integer next() {


        index++;
        int res, curentIndex = index, i = 0;
        for (i = 0; i < setOfGroups.length(); i++) {
            if (curentIndex < setOfGroups.getSet()[i].length()) {
                break;
            }
            if (curentIndex >= setOfGroups.getSet()[i].length()) {
                curentIndex -= setOfGroups.getSet()[i].length();
            }
        }
        return setOfGroups.getSet()[i].getData()[curentIndex];
    }
}
