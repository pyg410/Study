package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon25206 {
    static int all=0;
    static int allRecord = 0;
    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<20; i++){
            String[] input = br.readLine().split(" ");
            int record = changeInt(input[2]);
            if(record == -1) continue;
            int hak = (int)(Double.parseDouble(input[1]) * 10);
            all += hak;
            allRecord += hak * record;

        }

        // output
        System.out.println(Math.round((double)allRecord/(double)all * 100000)/1000000.0);

    }

    private static int changeInt(String input){
        if(input.equals("A+")){
            return 45;
        }
        if(input.equals("A0")){
            return 40;
        }
        if(input.equals("B+")){
            return 35;
        }
        if(input.equals("B0")){
            return 30;
        }
        if(input.equals("C+")){
            return 25;
        }
        if(input.equals("C0")){
            return 20;
        }
        if(input.equals("D+")){
            return 15;
        }
        if(input.equals("D0")) return 10;
        if(input.equals("F")) return 0;

        return -1;
    }
}
