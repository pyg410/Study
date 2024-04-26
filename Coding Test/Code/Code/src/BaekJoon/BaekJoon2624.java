package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2624 {
    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int coin[][] = new int[k+1][2];
        for(int i =1; i<=k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken()); // 동전 가치
            coin[i][1] = Integer.parseInt(st.nextToken()); // 동전 개수
        }
        int dp[][] = new int[k+1][t+1];

        // main logic
        coin[0][0] = 0;
        coin[0][1] = 0;
        Arrays.sort(coin, (a, b) -> a[0]-b[0]);
        for(int i=0; i<=k; i++){
            dp[i][0] = 1;
        }


        for(int coinIndex =1; coinIndex<=k; coinIndex++){

            int coinValue = coin[coinIndex][0];
            int coinNum = coin[coinIndex][1];

            for(int money =1; money<=t; money++){
                for(int coinCnt = 0; coinCnt <=coinNum; coinCnt++){
                    if(money-(coinCnt*coinValue) < 0) break;
                    dp[coinIndex][money] = dp[coinIndex][money] + dp[coinIndex-1][money-(coinValue*coinCnt)];
                }
            }

        }
        // output
        System.out.println(dp[k][t]);
    }



}
