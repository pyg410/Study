package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()); // 채워야하는 최소 인원
        int n = Integer.parseInt(st.nextToken()); // 도시 개수

        int[] dp = new int[c + 101];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for(int j = people; j<c+101; j++){
                if(dp[j-people] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], cost + dp[j-people]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i=c; i < c+101; i++){
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);

    }
}
