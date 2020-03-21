package university_;

import java.util.Objects;

/**
 * Hello world!
 *
 */
public class Data
{
   private String name;
   private double value;

    public Data(String name, double value) {
       setName(name);
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null && !"".equals(name)){ this.name = name;}
        else
        {throw new NullPointerException("illegal argument");}

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Double.compare(data.value, value) == 0 &&
                Objects.equals(name, data.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
