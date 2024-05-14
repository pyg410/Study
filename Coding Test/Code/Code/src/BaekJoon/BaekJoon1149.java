package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon1149 {

    private static int[][] dp;
    private static int[][] house;
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        house = new int[n][3];
        dp = new int[n][3];

        for(int i=0; i<n; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            house[i][RED] = input[RED];
            house[i][GREEN] = input[GREEN];
            house[i][BLUE] = input[BLUE];
        }

        dp[0][RED] = house[0][RED];
        dp[0][GREEN] = house[0][GREEN];
        dp[0][BLUE] = house[0][BLUE];

        // main logic

        int result = Math.min(painting(n-1, RED), Math.min(painting(n-1, GREEN), painting(n-1, BLUE)));


        // output

        System.out.println(result);



    }

    private static int painting(int n, int color){

        if(dp[n][color]==0){
            switch (color){
                case RED:
                    dp[n][RED] = Math.min(painting(n-1, GREEN), painting(n-1, BLUE)) + house[n][RED];
                    break;
                case GREEN:
                    dp[n][GREEN] = Math.min(painting(n-1, RED), painting(n-1, BLUE)) + house[n][GREEN];
                    break;
                case BLUE:
                    dp[n][BLUE] = Math.min(painting(n-1, GREEN), painting(n-1, RED)) + house[n][BLUE];
                    break;
            }
        }

        return dp[n][color];

    }
}
