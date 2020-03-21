package university_;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class DataTest {
    /**
     * Rigorous Test :-)
     */





    @Test
    public void testUnionSet() {
        List<Set<Data>> list = new ArrayList<>();
        Set<Data> set1 = new HashSet<>();
        Set<Data> set2 = new HashSet<>();
        set1.add(new Data("qww", 123));
        set1.add(new Data("asd", 234));
        set2.add(new Data("ert", 567));
        set2.add(new Data("ghn", 756));
        list.add(set1);
        list.add(set2);
        Set<Data> res = new HashSet<>();
        res.add(new Data("qww", 123));
        res.add(new Data("asd", 234));
        res.add(new Data("ert", 567));
        res.add(new Data("ghn", 756));
        assertEquals(res, Service.getUnionSet(list));

    }

    @Test
    public void testIntersects() {
        List<Set<Data>> list = new ArrayList<>();
        Set<Data> set1 = new HashSet<>();
        Set<Data> set2 = new HashSet<>();
        set1.add(new Data("qww", 123));
        set1.add(new Data("asd", 234));
        set1.add(new Data("q", 13));
        set1.add(new Data("as", 24));
        set2.add(new Data("ert", 567));
        set2.add(new Data("ghn", 756));
        set2.add(new Data("qww", 123));
        set2.add(new Data("asd", 234));
        list.add(set1);
        list.add(set2);
        Set<Data> res = new HashSet<>();
        res.add(new Data("qww", 123));
        res.add(new Data("asd", 234));
        assertEquals(res, Service.getIntersectsSet(list));


    }


}
