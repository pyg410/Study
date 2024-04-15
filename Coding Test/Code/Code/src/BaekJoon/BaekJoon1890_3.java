package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1890_3 {
    static int n;
    static int[][] graph;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new long[n][n];
        for(int i=0; i<n; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }

        // main logic
        dpFuc();


        // output
        System.out.println(dp[n-1][n-1]);

    }
    private static void dpFuc(){
        dp[0][0]=1;

        for(int i=0; i<n; i++){
            for(int j =0; j<n; j++){

                int next = graph[i][j];
                if(next ==0) break;

                if(i+next <n) dp[i+next][j] += dp[i][j];
                if(j+next <n) dp[i][j+next] += dp[i][j];
            }
        }

    }
}
