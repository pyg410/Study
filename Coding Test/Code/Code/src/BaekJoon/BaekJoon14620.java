package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon14620 {
    static int[][] arr;
    static boolean[][] bought;
    static int N;
    static int level;
    static int result= Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N input
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        bought = new boolean[N][N];

        // arr input
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // main logic
        getAnswer(0, 0);

        // output
        System.out.println(result);

    }

    private static void getAnswer(int level, int answer) {

        if(level == 3){
            result = Math.min(result, answer);
            return;
        }
        for(int i = 1; i<= N -2; i++){
            for(int j = 1; j<= N -2; j++){
                if(bought[i][j] || bought[i-1][j] || bought[i+1][j] || bought[i][j-1] || bought[i][j+1]) continue;
                bought[i][j] = true;
                bought[i+1][j] = true;
                bought[i-1][j] = true;
                bought[i][j+1] = true;
                bought[i][j-1] = true;
                int sum = arr[i][j] + arr[i-1][j] + arr[i+1][j] + arr[i][j-1] + arr[i][j+1];
                getAnswer(level+1, answer+sum);
                bought[i][j] = false;
                bought[i+1][j] = false;
                bought[i-1][j] = false;
                bought[i][j+1] = false;
                bought[i][j-1] = false;

            }
        }
    }
}
