package Ymp3;

import java.util.Objects;

/**
 * Hello world!
 *
 */
public class Packing
{
    private String name;
    private int weight;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Packing(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Packing{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packing packing = (Packing) o;
        return weight == packing.weight &&
                name.equals(packing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
