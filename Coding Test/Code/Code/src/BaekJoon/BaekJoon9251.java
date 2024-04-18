package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class BaekJoon9251 {

    static int[][] LCS;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String[] stringA = br.readLine().split("");
        String[] stringB = br.readLine().split("");
        LCS = new int[stringA.length+1][stringB.length+1];

        // main logic
        LCS(stringA, stringB);

        // output
        System.out.println(LCS[stringA.length][stringB.length]);
    }

    private static void LCS(String[] stringA, String[] stringB) {

        for(int i=0; i<=stringA.length; i++){
            for(int j=0; j<=stringB.length; j++){
                if(i==0 || j==0){
                    LCS[i][j] = 0;
                } else if (Objects.equals(stringA[i-1], stringB[j-1])) {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
                }
            }
        }


    }
}
