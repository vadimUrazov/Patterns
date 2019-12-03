package Ymp3;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class ClassesFilterTest {
    @Test

    public void testBeginStringFilter() {
        BeginStringFilter b=new BeginStringFilter("мама мыла раму");
        assertFalse(b.apply("мама"));
        assertFalse(b.apply("рама"));
    }

    @Test
    public void testMyClassesFilter(){
        CompareFilter c=new CompareFilter("12345");
        EqualsFilter e=new EqualsFilter("235");
        assertFalse(e.apply("2374567"));
        assertFalse(c.apply("23"));
    }
}
