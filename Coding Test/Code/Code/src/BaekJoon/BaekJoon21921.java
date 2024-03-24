package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon21921 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        int resultCnt = 0;

        int left = 0;
        int right = X-1;
        int sum = 0;
        for(int i = left; i<=right; i++) {
            sum += arr[i];
        }
        while(right < N){

            if(sum == result){
                resultCnt++;
            } else if (sum > result) {
                resultCnt = 1;
                result = sum;
            }

            sum -= arr[left];

            left++;
            right++;
            if(right== N){
                break;
            }
            sum += arr[right];


        }

        if(result == 0){
            System.out.println("SAD");
        }else {
            System.out.println(result + "\n" + resultCnt);
        }

    }
}
