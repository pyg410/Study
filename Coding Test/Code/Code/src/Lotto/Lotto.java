package Lotto;

import java.util.HashSet;

public class Lotto {
    public static void main(String[] args) {
        HashSet<Integer> lotto = new HashSet<>();

        while(lotto.size()<6){
            lotto.add( (int) (Math.random()*45 + 1));
        }

        lotto.forEach((l) -> System.out.print(l + " "));
    }
}
