package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon7453 {
    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];
        for(int i =0; i<n; i++){
            long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            a[i] = input[0];
            b[i] = input[1];
            c[i] = input[2];
            d[i] = input[3];
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        Arrays.sort(d);

        // main logic
        int cnt = 0;
        for(int aIdx =0; aIdx<n; aIdx++){
            for(int bIdx =0; bIdx<n; bIdx++){

                int left = 0;
                int right = n-1;

                long sum = a[aIdx] + b[bIdx];

                while(true){
                    if(left > n-1) break;
                    if(right < 0) break;

                    sum += c[left] + d[right];

                    System.out.println("sum : " + sum);

                    int leftCnt =1;
                    int rightCnt =1;
                    if(sum == 0){
                        while(left+1 < n && c[left] == c[left+1]){
                            leftCnt++;
                            left++;
                        }
                        while(right-1 >= 0 && d[right] == d[right-1]){
                            rightCnt++;
                            right--;
                        }
                        cnt+= (long) leftCnt * rightCnt;
                    }
                    else if(sum < 0){
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }

        System.out.println(cnt);


    }
}
