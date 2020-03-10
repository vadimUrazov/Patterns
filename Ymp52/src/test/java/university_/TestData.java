package university_;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {
    @Test
    public void testConstructNoParametr() {
        Data obj = new Data();
        assertNotNull(obj);
        assertEquals(obj.getNameOfSet(), "Default Data");
    }


    @Test
    public void testSetters() {
        Data obj = new Data();
        obj.setNameOfSet("Polly");
        Group[] res = {new Group(1, 1, 2, 3), new Group(2, 2, 8)};
        obj.setSet(res);
        assertEquals("Polly", obj.getNameOfSet());
        assertArrayEquals(res, obj.getSet());

        obj.setSet(new Group(1, 1, 1, 1, 1), new Group(2, 2, 2, 2), new Group(3, 3));
        Group[] secondRes = {new Group(1, 1, 1, 1, 1), new Group(2, 2, 2, 2),
                new Group(3, 3)};
        assertArrayEquals(obj.getSet(), secondRes);
    }


    @Test
    public void testLength() {
        Data obj = new Data();
        Group[] res = {new Group(), new Group(1, 1, 2, 3)};
        obj.setSet(res);
        assertEquals(obj.length(), 2);

    }


}
