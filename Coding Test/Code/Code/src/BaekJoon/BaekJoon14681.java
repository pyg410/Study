package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon14681 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());

        int result= 0;
        if(x > 0){
            if(y>0){
                result =1;
            }else{
                result = 4;
            }
        }else{
            if(y>0){
                result = 2;
            }else{
                result = 3;
            }
        }
        System.out.println(result);
    }
}
