package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon5557_3 {
    static int n;
    static int[] inputArr;
    static long[][] dpArr;

    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inputArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
        dpArr = new long[n][21];


        // main logic
        dpArr[0][inputArr[0]] = 1;
        for(int i=1; i<n-1; i++){
            for(int j =0; j<=20; j++){
                if(dpArr[i-1][j] !=0){

                    int plusIndex = j+inputArr[i];
                    int minusIndex = j-inputArr[i];
                    if(0<= plusIndex && plusIndex <=20){
                        dpArr[i][plusIndex] += dpArr[i-1][j];
                    }
                    if(0<= minusIndex && minusIndex <=20){
                        dpArr[i][minusIndex] += dpArr[i-1][j];
                    }
                }

            }
        }

        // output
        System.out.println(dpArr[n-2][inputArr[n-1]]);

    }
}
