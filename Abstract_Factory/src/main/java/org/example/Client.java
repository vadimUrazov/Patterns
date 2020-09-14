package org.example;

/**
 * Hello world!
 *
 */
abstract class BakeryFactory{
    public abstract Baker createBaker();

    public abstract Cashier createCashier();

    public abstract Peddler createPeddler();
}
 interface Baker {
     void cook();
}

 interface Cashier {
     void count();
}

 interface Peddler {
     void transfer();
}
class PeopleBakeryFactory extends BakeryFactory{

    @Override
    public Baker createBaker() {
        return new BakerCook();
    }

    @Override
    public Cashier createCashier() {
        return new CashierCount();
    }

    @Override
    public Peddler createPeddler() {
        return new PeddlerTransfer();
    }
}
class BakerCook implements Baker{

    @Override
    public void cook() {
        System.out.println("baker");
    }
}
class CashierCount implements Cashier{

    @Override
    public void count() {
        System.out.println("cashier");
    }
}
class PeddlerTransfer implements Peddler{

    @Override
    public void transfer() {
        System.out.println("peddler");
    }

}

public class Client {
    public static void create(BakeryFactory factory) {
        Baker baker = factory.createBaker();
        System.out.println(baker.toString());
       Cashier  cashier = factory.createCashier();
        System.out.println(cashier.toString());
        Peddler peddler = factory.createPeddler();
        System.out.println(peddler.toString());
    }
    public static void main( String[] args )
    {
     create(new PeopleBakeryFactory());
    }
}
