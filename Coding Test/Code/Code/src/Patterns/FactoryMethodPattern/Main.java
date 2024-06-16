package Patterns.FactoryMethodPattern;

public class Main {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        creator.someOperation();

        Creator creator1 = new ConcreteCreator1();
        creator1.someOperation();
    }
}
