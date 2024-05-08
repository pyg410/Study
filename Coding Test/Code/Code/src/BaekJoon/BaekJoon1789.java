package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1789 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long s = Long.parseLong(br.readLine());

        long sum = 0;
        int cnt = 0;
        for(int i=1; ; i++){
            if(sum > s){
                break;
            }
            sum += i;
            cnt++;
        }

        // output
        System.out.println(cnt-1);
    }
}
