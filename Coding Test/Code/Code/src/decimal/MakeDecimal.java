package decimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MakeDecimal {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());


        // 해당 숫자 소수 판별만 할때
        if(solve(num)){
            System.out.println("소수");
        }else{
            System.out.println("소수 아님");
        }

        // 해당 숫자이하에 모든 소수를 구할때
        for(int i = 0; i <= num; i++){
            if(solve(i)){
                System.out.printf("%d는 소수\n",i);
            }else{
                System.out.printf("%d는 소수 아님\n",i);
            }
        }
    }

    public static boolean solve(int num){

        // 0 또는 1은 소수가 아님
        if(num < 2) return false;

        // 2는 소수
//        if(num == 2) return true;

        // 그외의 수(루트이하의 수로 나눠보기)
        for(int i = 2; i <= Math.sqrt(num); i++){

            //루트이하 수에서 나눠지는 수가 있으면 소수가 아님
            if(num % i == 0){
                return false;
            }
        }

        //안나왔으면 소수
        return true;
    }
}
