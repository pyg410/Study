package FindZero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FindZero {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cnt=0;

        for(int i=1; i<=n; i++){
            int num = i;
            while(num%5==0){
                cnt++;
                num/=5;
            }
        }
        System.out.println(cnt);
    }

}

