package university_;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class InputOutput {
    public static void outputStreamArrayInt(File file, int[] array) throws IOException {
        try (FileOutputStream f = new FileOutputStream(file)) {
            for (int i = 0; i < array.length; i++)
                f.write(array[i]);
        }

    }

    public static int[] inputStreamArrayInt(File file, int n) throws IOException {

        try (FileInputStream f = new FileInputStream(file)) {
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = f.read();
            }
            return array;
        }

    }

    public static void outputStreamArray(File file, int[] array) throws IOException {
        try (FileWriter f = new FileWriter(file)) {

            for (int i = 0; i < array.length; i++)
                f.write(array[i]);
            f.write(' ');
        }

    }

    public static int[] inputStreamSymbolArray(File file, int n) throws IOException {

        try (FileReader f = new FileReader(file)) {
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = f.read();
            }

            return array;
        }

    }


    public static int[] outputRandStreamArray(File file, int j, int n) throws IOException {
        int[] arr = new int[n];
        try (RandomAccessFile f = new RandomAccessFile(file, "file.txt")) {
            f.seek(j);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = f.readInt();
            }
            return arr;
        }

    }

    public static List<File> getListFile(File file, String expansion, String catalog) {
        List<File> list = new LinkedList<>();
        LinkedList<File> dirList = new LinkedList<>();
        if (file.isDirectory()) {
            dirList.addLast(file);
        }
        while (dirList.size() > 0) {
            File[] filesList = dirList.getFirst().listFiles();
            if (filesList != null) {
                for (File path : filesList) {
                    if (path.getParent().equals(catalog) && path.getName().endsWith(expansion)) {
                        list.add(path);
                    }

                }

            }
            dirList.removeFirst();
        }
        return list;
    }

}