package org.example;

/**
 * Hello world!
 */
interface Implementor {
    void operationImp();
}

abstract class Abstraction {
    private Implementor imp;

    public Abstraction(Implementor implementor) {
        imp = implementor;
    }

    public void setImp(Implementor imp) {
        this.imp = imp;
    }

    public void operation() {
        imp.operationImp();
    }

}

class RefinedAbstracion extends Abstraction {
    public RefinedAbstracion(Implementor implementor) {
        super(implementor);
    }

    public void Operation() {
        super.operation();
    }
}

class ConcretteImplementorA implements Implementor {
    public void operationImp() {
        System.out.println(2 * 2);
    }
}

class ConcretteImplementorB implements Implementor {
    public void operationImp() {
        System.out.println(4 * 4);
    }
}

public class Client {
    public static void main(String[] args) {
        //Начальная инициализация с реализации A
        RefinedAbstracion refinedAbstraction = new RefinedAbstracion(new ConcretteImplementorA());
        refinedAbstraction.Operation();
//Изменяем используемый вариант на реализацию B
        refinedAbstraction.setImp(new ConcretteImplementorB());
        refinedAbstraction.Operation();

    }
}
