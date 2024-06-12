package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon19637 {

    private static int N, M;
    private static String[] styleName;
    private static int[] stylePower;
    private static int[] userPower;
    private static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // input
        input();

        // main logic
        solution();

        // output
        System.out.println(output);

    }

    private static void solution() {

        for(int i =0; i<M; i++){
            int user = userPower[i];

            int start = 0;
            int end = N-1;

            while(start<= end){
                int mid = (start+end)/2;

                if(stylePower[mid] < user){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }

            output.append(styleName[start]).append("\n");
        }

    }

    private static void input() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = input[0];
        M = input[1];

        styleName = new String[N];
        stylePower = new int[N];

        for(int i = 0; i< N; i++){
            String[] style = br.readLine().split(" ");
            styleName[i] = style[0];
            stylePower[i] = Integer.parseInt(style[1]);
        }

        userPower = new int[M];
        for(int i =0; i<M; i++){
            userPower[i] = Integer.parseInt(br.readLine());
        }

    }
}
