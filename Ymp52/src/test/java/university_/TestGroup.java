package university_;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGroup {

    @Test
    public void testConstructWithDataAndCopy()  {
        Group obj = new Group(100, 1, 2, 3, 4, 5);
        assertNotNull(obj);
        Integer[]res = {1,2,3,4,5};

        assertEquals(obj.getId(), 100);
        assertArrayEquals(obj.getData(), res);
        Group copyObj = new Group(obj);
        assertNotNull(copyObj);
        assertEquals(copyObj.getId(), 100);
        assertArrayEquals(copyObj.getData(), res);
    }

    @Test
    public  void testCopyWithNewIdAndSetters() {
        Group obj = new Group();
        obj.setId(23);
        obj.setData(1, 8, 0, 9);
        Integer[] res = {1,8,0,9};


        Group copyObj = new Group(obj, 10);
        assertArrayEquals(obj.getData(), res);
        assertArrayEquals(copyObj.getData(), obj.getData());
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


}
