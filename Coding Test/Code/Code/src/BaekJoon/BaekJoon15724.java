package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon15724 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] graph = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i<n+1; i++){
            input = br.readLine().split(" ");
            for(int j= 1; j<m+1; j++){
                graph[i][j] = Integer.parseInt(input[j-1]);
                dp[i][j] = graph[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        int k = Integer.parseInt(br.readLine());
        for(int i =0; i<k; i++){
            // main logic
            input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);

            int result = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];


            System.out.println(result);
        }


    }
}
