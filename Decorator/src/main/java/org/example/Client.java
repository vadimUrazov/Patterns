package org.example;

/**
 * Hello world!
 */
interface Component {
    void operation();
}
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("Operation");
    }
}
abstract class Decorator {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public abstract void operation();
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("Concrete decorator A");
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("Concrete decorator B");
    }
}

public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operation();
        Decorator decoratorA=new ConcreteDecoratorA(component);
        decoratorA.operation();
        Decorator decoratorB=new ConcreteDecoratorB(component);
        decoratorB.operation();

           }
}
