package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon7453_2 {
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

        //main logic
        long[] ab = new long[n*n];
        long[] cd = new long[n*n];

        int idx=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                ab[idx] = a[i] + b[j];
                cd[idx++] = c[i] + d[j];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        long cnt = 0;
        int left =0; int right= n*n-1;
        while(left < n*n && right >=0){
            if(ab[left] + cd[right] <0) left++;
            else if(ab[left] + cd[right] > 0) right--;
            else{
                int leftCnt =1;
                int rightCnt = 1;
                while(left+ 1 < n*n && ab[left] == ab[left+1]){
                    leftCnt++;
                    left++;
                }
                while(right-1 >=0 && cd[right] == cd[right-1]){
                    rightCnt++;
                    right--;
                }
                cnt += (long) leftCnt * rightCnt;
                left++;
            }
        }

        System.out.println(cnt);

    }
}
