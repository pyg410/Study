package ListLoop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListLoop {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Coconut", "Lemon", "Melon");


    }

    static void listLoop1(List<String> list){
        for(int i =0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    static void listLoop2(List<String> list){
        for(Iterator<String> iter = list.iterator(); iter.hasNext(); ){
            System.out.println(iter.next());
        }
    }

    static void listLoop3(List<String> list){
        for(String fruit : list){
            System.out.println(fruit);
        }
    }

    static void listLoop4(List<String> list){
        list.forEach(System.out::println);
    }
}
