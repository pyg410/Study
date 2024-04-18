package Refractoring;

import java.util.Enumeration;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        //실험할 코드 추가
        CustomerAfterRefactor_2 c = new CustomerAfterRefactor_2("이름1", new Vector());
        for(int i=0; i<10; i++){
            c.addRental(new Rental(new Movie("영화1", 1), 100));
        }
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        System.out.println(c.statement());
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(ms) : "+secDiffTime);
    }

}
