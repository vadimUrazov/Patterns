package university_;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class TestCollectionDemo {
   @Test
    public void testCountStringBeginChar(){
       List<String> list=new ArrayList<>();
       list.add("Yasdf");
       list.add("ASDFGHJ");
       list.add("AcHKL");
       list.add("axghj");
     assertEquals(3,CollectionDemo.countStringBeginChar(list,'a'));
   }
}
