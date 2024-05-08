package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon20366 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // main logic
        int result = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for(int first =0; first<n-1; first++){
            for(int second =first+1; second<n; second++){
                int left = 0;
                int right = n-1;
                while(left<right){
                    if(left == first || left == second) {
                        left++;
                        continue;
                    }
                    if(right == second || right == first) {
                        right--;
                        continue;
                    }

                    int firstSnowman = arr[first] + arr[second];
                    int secondSnowman = arr[left] + arr[right];
                    int diff = firstSnowman-secondSnowman;
                    result = Math.min(Math.abs(diff), result);

                    if(diff == 0){
                        System.out.println(0);
                        return;
                    }
                    if(diff < 0){
                        right --;
                    }else{
                        left++;
                    }


                }
            }
        }

        // output
        System.out.println(result);

    }
}
