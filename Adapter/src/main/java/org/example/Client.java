package org.example;

/**
 * Hello world!
 *
 */
class FahrenheitSensor {

    public double getFahrenheitTemp(){
        double f=32.0;// температура в градусах Цельсия, параметр который нужно перевести в Фаренгейт
return f;
    }

}
abstract class Sensor{
    double getTemperature(){
        return 0;
    }
}
class Adapter extends Sensor{
private FahrenheitSensor p;
    public Adapter(FahrenheitSensor p){
        this.p=p;
    }
    double getTemperature(){
        return (p.getFahrenheitTemp()-32)*5.0/9.0;
    }
}

public class Client
{
    public static void main( String[] args )
    {
        Sensor s=new Adapter(new FahrenheitSensor());

        System.out.println( s.getTemperature());
    }
}
