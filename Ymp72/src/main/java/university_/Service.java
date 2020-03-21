package university_;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class Service{
public ObjectOutput serializeHouse(ObjectOutput stream, House house){
    try {
        stream.writeObject(house);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return stream;}
    public House deserializeHouse(ObjectInput stream, House house){
        try {
            house=(House)stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return house;}
}

