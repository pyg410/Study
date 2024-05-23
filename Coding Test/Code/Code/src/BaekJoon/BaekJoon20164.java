package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon20164 {
    static String N;
    static int NLength;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // input
        init();

        // main logic
        String n = N;
        solution(n, oddCnt(n));

        // output
        System.out.println(MIN + " " + MAX);
    }

    private static void solution(String n, int cnt) {
        // 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.

        // 수가 한 자리이면 더이상 아무것도 하지 않고 종료한다.
        if(n.length() == 1) {
            MAX = Math.max(MAX, cnt);
            MIN = Math.min(MIN, cnt);
            return;
        }
        // 수가 두자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
        else if(n.length() == 2) {
            // 2개로 나눈다
            int[] nArr = Arrays.stream(n.split("")).mapToInt(Integer::parseInt).toArray();

            // 합을 구한다.
            int newN = nArr[0] + nArr[1];

            solution(String.valueOf(newN),cnt+oddCnt(String.valueOf(newN)));
        }
        // 수가 세 자리 이상이면 임이의 위치에서 끊어서 3개의 수로 분할하고 3개의 더한 값을 새로운 수로 생각한다.
        else{
            int len = n.length();
            for(int i =0; i<= len-3; ++i){
                for(int j = i+1; j<=len-2; ++j){
                    // 3개의 수로 분할 한다.
                    int firstNum = Integer.parseInt(n.substring(0,i+1));
                    int secondNum = Integer.parseInt(n.substring(i+1, j+1));
                    int thirdNum = Integer.parseInt(n.substring(j+1, len));
                    int sum = firstNum+secondNum+thirdNum;
                    String sumStr = String.valueOf(sum);

                    solution(sumStr, cnt+oddCnt(sumStr));
                }
            }
        }
    }

    private static int oddCnt(String n){
        int[] nArr = Arrays.stream(n.split("")).mapToInt(Integer::parseInt).toArray();

        int oddCnt = 0;
        for(int i =0; i<nArr.length; i++){
            if(nArr[i] % 2 != 0) oddCnt++;
        }
        return oddCnt;

    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();
        NLength = N.length();
    }

}
