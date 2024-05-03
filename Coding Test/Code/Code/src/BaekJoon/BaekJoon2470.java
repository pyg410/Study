package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2470 {

    private static int left;
    private static int right;
    private static int n;
    private static int[] arr;
    private static int resultLeft;
    private static int resultRight;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        // main logic

        left = 0;
        right = arr.length-1;

        solution();

        // output
        System.out.println(resultLeft + " " + resultRight);
    }

    private static void solution() {
        int min = Integer.MAX_VALUE;
        while(left < right){
            int sum = arr[left] + arr[right];

            if(min > Math.abs(sum)){
                min = Math.abs(sum);
                resultLeft = arr[left];
                resultRight = arr[right];
                if(sum == 0) return;
            }


            if(sum >0){
                right--;
            }
            else {
                left++;
            }

        }
    }
}
