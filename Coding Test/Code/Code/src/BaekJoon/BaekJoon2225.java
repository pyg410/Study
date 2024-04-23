package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2225 {
    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long dp[][] = new long[n+1][k+1];

        // main logic
        for(int i =0; i<=n; i++){
            for(int j =1; j<=k; j++){
                if(i==0 || j==1){
                    dp[i][j] = 1;
                }else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_000;
                }
            }
        }

        // output
        System.out.println(dp[n][k]);

    }
}
