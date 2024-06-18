package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon1477 {

    private static int n,m,l;
    private static int[] restArea;

    private static int maxLen;

    public static void main(String[] args) throws IOException {

        // input
        input();

        // main logic
        solution();

        // output
        System.out.println(maxLen);
    }

    private static void solution() {

        int start = 1;
        int end = l-1;

        while(start <= end){

            int mid = (start+end)/2;
            int sum = 0;
            for(int i = 1; i< restArea.length; i++){
                sum += (restArea[i] - restArea[i-1] - 1)/mid;
            }

            if(sum > m){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        maxLen = start;

    }

    private static void input() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        l = Integer.parseInt(input[2]);

        restArea = new int[n+2];
        restArea[0] = 0;
        input = br.readLine().split(" ");
        for(int i =1; i<= n; i++){
            restArea[i] = Integer.parseInt(input[i-1]);
        }
        restArea[n+1] = l;

        Arrays.sort(restArea);
    }
}
