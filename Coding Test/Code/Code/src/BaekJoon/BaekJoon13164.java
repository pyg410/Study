package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon13164 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 원생 수
        int K = Integer.parseInt(input[1]); // 조의 개수

        int[] kids = new int[N]; // 원생 array
        input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            kids[i] = Integer.parseInt(input[i]);
        }

        // main logic
        int[] diff = new int[N-1];
        for(int i =1; i<N; i++){
            diff[i-1] = kids[i] - kids[i-1];
        }
        Arrays.sort(diff);

        int result = 0;
        for(int i =0; i<K-1; i++){
            result += diff[i];
        }
        System.out.println(result);

    }
}
