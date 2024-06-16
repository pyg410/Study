package Patterns.FactoryMethodPattern;

public class ConcreteProduct extends Product{

    @Override
    void performAction() {
        System.out.println("concreteProduct");
    }
}
