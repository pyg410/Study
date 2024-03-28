package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon5618 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int num = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            num = Math.min(num, arr[i]);
        }
        for(int i =1; i<=num; i++){
            int count = 0;
            for(int j =0; j<n; j++){
                if(arr[j] % i == 0){
                    count++;
                }
            }
            if(count ==n)
                System.out.println(i);
        }
    }
}
