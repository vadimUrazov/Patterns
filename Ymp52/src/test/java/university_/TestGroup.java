package university_;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGroup {
    @Test
    public  void testConstructDefaultAndGetters() {
        Group obj = new Group();
        assertNotNull(obj);
        assertEquals(obj.getId(), 1);
        assertEquals(obj.getData(), new ArrayList<Integer>());
    }

    @Test
    public void testConstructWithDataAndCopy()  {
        Group obj = new Group(100, 1, 2, 3, 4, 5);
        assertNotNull(obj);
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(4);
        res.add(5);
        assertEquals(obj.getId(), 100);
        assertEquals(obj.getData(), res);
        Group copyObj = new Group(obj);
        assertNotNull(copyObj);
        assertEquals(copyObj.getId(), 100);
        assertEquals(copyObj.getData(), res);
    }

    @Test
    public  void testCopyWithNewIdAndSetters() {
        Group obj = new Group();
        obj.setId(23);
        obj.setData(1, 8, 0, 9);
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(8);
        res.add(0);
        res.add(9);
        Group copyObj = new Group(obj, 10);
        assertEquals(obj.getData(), res);
        assertEquals(copyObj.getData(), obj.getData());
        assertNotEquals(copyObj.getId(), obj.getId());
    }

    @Test
    public  void testLength()  {
        Group obj = new Group(23, 10, 18, 0, 9, 3, 1, 0, 7);
        assertEquals(obj.length(), 8);
    }

    @Test
    public  void testConstructException() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            Group obj = new Group(-45, 1);

        });

        assertNotNull(thrown.getMessage());

    }

    @Test
    public  void testSetException(){


        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            Group obj = new Group(54, 1);
            obj.setId(0);

        });

        assertNotNull(thrown.getMessage());

    }

    @Test
    public static void testSetAndAddData() {
        Group obj = new Group(12, 23, 3,5,6,7,83,343);
        obj.setData(1,2,3);
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        assertEquals(obj.getData(), res);
        obj.addData(2, 1, 0);
        res.add(2);
        res.add(1);
        res.add(0);
        assertEquals(obj.getData(), res);
        ArrayList<Integer> something = new ArrayList<>();
        something.add(1);
        something.add(0);
        something.add(5);
        obj.setData(something);
        assertEquals(obj.getData(), something);
        something.clear();
        res.clear();
        something.add(9);
        something.add(8);
        something.add(4);
        res.add(1);
        res.add(0);
        res.add(5);
        res.add(9);
        res.add(8);
        res.add(4);
        obj.addData(something);
        assertEquals(obj.getData(), res);
    }
}
