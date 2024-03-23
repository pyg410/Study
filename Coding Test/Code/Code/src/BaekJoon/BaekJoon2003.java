package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 배열길이
        int M = Integer.parseInt(st.nextToken()); // 합

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int result = 0;

        while(left < N){

            // 합이 M을 초과 혹은 오른쪽 포인터가 N의 범위를 벗어났을 때.
            if(sum > M || right == N){
                sum -= arr[left];// 왼쪽 포인터를 빼주고
                left++;
            }
            else {// 합초과하지 않은 경우 right 포인터를 왼쪽으로 옮긴다 .
                sum += arr[right];
                right++;

            }

            // 해당 조건을 달성하면
            if(sum == M){
                result++;
            }

        }

        System.out.println(result);



    }
}
