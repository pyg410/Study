package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon5557 {

    static int n;
    static int[] inputArr;
    static int[] dpArr;

    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inputArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
        dpArr = new int[21];

        // main logic
        int index = inputArr[0];
        dpArr[index] = 1;
        solution(1, index);

        int result = 0;
        for(int i=0; i<=20; i++){
            result = Math.max(result, dpArr[i]);
        }

        // output
        System.out.println(result);



    }

    private static void solution(int inputIndex, int dpIndex){

        if(inputIndex == n){//탈출조건
            return;
        }

        int plusIndex = dpIndex+inputArr[inputIndex];
        int minusIndex = dpIndex-inputArr[inputIndex];

        if(0<= plusIndex && plusIndex <=20){
            dpArr[plusIndex] = dpArr[dpIndex]+1;
            solution(inputIndex+1, plusIndex);
        }
        if(0<= minusIndex && minusIndex <=20){
            dpArr[minusIndex] = dpArr[dpIndex]+1;
            solution(inputIndex+1, minusIndex);
        }

    }
}
