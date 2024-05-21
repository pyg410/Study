package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon3079 {

    private static int n;
    private static long m;
    private static long[] time;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        n = (int)input[0]; // 입국 심사대 개수
        m = input[1]; // 상근이와 친구들
        time = new long[n];
        for(int i =0; i<n; i++){
            time[i] = Integer.parseInt(br.readLine());
        }

        // main logic
        Arrays.sort(time);

        long left = time[0];
        long right = time[n-1] * m;

        long result = Long.MAX_VALUE;

        while(left <= right){
            long mid = (left+right)/2;

            long cnt = 0; // 최대로 수용 가능한 시간초
            for(long t : time){
                cnt += mid/t;

                if(cnt >= m){
                    break;
                }
            }

            if(cnt < m){
                left = mid +1;
            }else{
                right = mid -1;
                result = Math.min(mid, result);
            }
        }


        // output

        System.out.println(result);

    }
}
