package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2110 {

    private static int n, c;
    private static int result=0;
    private static int[] house;
    public static void main(String[] args) throws IOException {

        // input
        input();

        // main logic
        solution();

        // output
        System.out.println(result);
    }

    private static void solution() {

        int left = 1;
        int right = house[house.length-1] - house[0] + 1;

        while(left < right){
            int mid = (left+right)/2;

            if(findSetCNum(mid) < c){
                right = mid;
            }else{
                left = mid +1;
            }
        }

        result = left-1;


    }

    private static int findSetCNum(int minimumDistance) {

        int count = 1;

        int lastHouse = house[0];

        for(int i =1; i<house.length; i++){

            int houseDistance = house[i] - lastHouse;

            if(houseDistance >= minimumDistance){
                count++;
                lastHouse = house[i];
            }
        }

        return count;

    }

    private static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        house = new int[n];

        for(int i =0; i<n; i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);
    }
}
