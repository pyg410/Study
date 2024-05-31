package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BaekJoon2346 {

    private static int n;
    private static int[] balloon;
    private static boolean[] boom;
    private static ArrayList<Integer> output = new ArrayList<>();
    private static int idx;
    public static void main(String[] args) throws IOException {

        // input
        init();

        // main logic
        solution();

        // output
        StringBuilder sb = new StringBuilder();
        output.stream().forEach(i -> sb.append(i).append(" "));
        System.out.println(sb);


    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        boom = new boolean[n];
        balloon = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }

    public static void solution(){
        idx = 0;

        while(output.size()<n){
            output.add(idx+1);
            boom[idx] = true;

            int moveNum = balloon[idx];

            if(output.size()==n) break;

            move(moveNum);

        }

    }

    public static void move(int num){

        if(output.size()==n-1){
            for(int i =0; i<boom.length; i++){
                if(!boom[i]){
                    idx = i;
                }
            }
            return;
        }

        if(num < 0){
            if(idx==0){
                idx = n-1;
            }else{
                idx = idx-1;
            }
            if(boom[idx]){
                move(num);
            }else{
                move(num+1);
            }

        }else if(num >0){
            if(idx == n-1){
                idx = 0;
            }else{
                idx = idx+1;
            }
            if(boom[idx]){
                move(num);
            }else{
                move(num-1);
            }

        }else{
            return;
        }

    }
}
