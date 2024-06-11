package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1193 {
    public static void main(String[] args) throws IOException {
        // main logic
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        int crossCnt = 1;
        int prevCountSum = 0;

        while(true){

            if( x <= prevCountSum + crossCnt){
                if(crossCnt %2 == 1){
                    System.out.println((crossCnt - (x-prevCountSum -1)) + "/" + (x-prevCountSum));
                    break;
                }
                else{
                    System.out.println((x-prevCountSum) + "/" + (crossCnt-(x-prevCountSum -1)));
                    break;
                }
            }

            else{
                prevCountSum += crossCnt;
                crossCnt++;
            }
        }

    }
}
