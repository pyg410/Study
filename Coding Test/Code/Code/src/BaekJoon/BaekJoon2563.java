package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2563 {

    private static boolean[][] isBlack;
    private static int colorPaperNum;
    private static final int blackPaperLength = 10;
    private static int output = 0;
    public static void main(String[] args) throws IOException {

        // input
        init();

        // main logic
        solution();

        // output
        System.out.println(output);
    }

    private static void solution() {
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(isBlack[i][j]) output++;
            }
        }
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isBlack = new boolean[100][100];
        colorPaperNum = Integer.parseInt(br.readLine());

        for(int i=0; i<colorPaperNum; i++){

            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int j = input[0]; j< input[0] + blackPaperLength; j++){
                if(j > 99) break;
                for(int k= input[1]; k<input[1] + blackPaperLength; k++){
                    if(k > 99) break;
                    isBlack[j][k] = true;
                }
            }

        }


    }
}
