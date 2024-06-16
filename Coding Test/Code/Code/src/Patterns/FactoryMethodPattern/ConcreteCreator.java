package Patterns.FactoryMethodPattern;

public class ConcreteCreator extends Creator{

    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
