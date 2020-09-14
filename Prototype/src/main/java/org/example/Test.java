package org.example;

/**
 * Hello world!
 */
interface Copyable {
    Copyable copy();
}

class ComplicatedObject implements Copyable {
    private Type type;

    enum Type {
        ONE, TWO
    }

    public ComplicatedObject copy() {
        return new ComplicatedObject();
    }

    public void setType(Type type) {
        this.type = type;
    }
}

public class Test {
    public static void main(String[] args) {
        ComplicatedObject prototype = new ComplicatedObject();

        ComplicatedObject clone = prototype.copy();

        clone.setType(ComplicatedObject.Type.ONE);
    }
}