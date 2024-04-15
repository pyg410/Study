package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1890_2 {

    static int n;
    static int[][] graph;
    static int[][] dp;
    static int[] dx = {1, 0}; // 우, 하
    static int[] dy = {0, 1}; // 우, 하

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new int[n][n];
        for(int i=0; i<n; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }

        // main logic
        dp[0][0]=1;

        dpFunc(0, 0);

        System.out.println(dp[n-1][n-1]);

    }

    private static void dpFunc(int y, int x){
        if(y == n-1 && x == n-1){
            return ;
        }

        for(int k=0; k<2; k++){
            int a = y + (dy[k]*graph[y][x]);
            int b = x + (dx[k]*graph[y][x]);
            if(0<= a && a <n && 0<= b && b < n){
                dp[a][b] += dp[y][x];
                dpFunc(a, b);
            }
        }
    }
}
