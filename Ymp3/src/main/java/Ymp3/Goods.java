package Ymp3;

import java.util.Objects;

public class Goods implements PackedGoods{
    private String name;
    private String description;

    public Goods(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public int weightNetto() {
        return 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isPackegeSet() {
        return false;
    }

    @Override
    public boolean isWeight() {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int weightBruto() {
        return 0;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods product = (Goods) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
