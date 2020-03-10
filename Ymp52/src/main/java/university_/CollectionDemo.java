package university_;

import java.util.List;


public class CollectionDemo {
    public static int countStringBeginChar(List<String> list, char c) {
        int count = 0;
        for (String string : list) {
            if (string.toLowerCase().charAt(0) == c) {
                count++;
            }
        }
        return count;
    }

}
