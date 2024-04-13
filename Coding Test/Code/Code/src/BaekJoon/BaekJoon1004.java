package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1004 {
    public static void main(String[] args) throws IOException {

        // testcase input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // testcase logic
        for(int i =0; i< T; i++){

            // main input
            String[] input = br.readLine().split(" ");
            int[] startPoint = new int[2];
            int[] destination = new int[2];
            startPoint[0] = Integer.parseInt(input[0]);
            startPoint[1] = Integer.parseInt(input[1]);
            destination[0] = Integer.parseInt(input[2]);
            destination[1] = Integer.parseInt(input[3]);
            int n = Integer.parseInt(br.readLine());

            int[][] planet = new int[n][3];
            int cnt = 0;
            for(int j=0; j<n; j++){
                input = br.readLine().split(" ");
                planet[j][0] = Integer.parseInt(input[0]); // 중점x
                planet[j][1] = Integer.parseInt(input[1]); // 중점y
                planet[j][2] = Integer.parseInt(input[2]); // 반지름 r

                // main logic

                double startToPlanetDistance = getDistance(startPoint[0], startPoint[1], planet[j][0], planet[j][1]);
                double destinationToPlanetDistance = getDistance(destination[0], destination[1], planet[j][0], planet[j][1]);

                if(startToPlanetDistance < planet[j][2] && destinationToPlanetDistance < planet[j][2]){ // 출발점과 도착점이 모두 행성계 안에 존재하는 경우
                    continue;
                }
                if(startToPlanetDistance < planet[j][2]){ // 출발점이 행성계 내에 존재하는 경우
                    cnt++;
                }
                if(destinationToPlanetDistance < planet[j][2]){// 도착점이 행성계 내에 존재하는 경우
                    cnt++;
                }
            }

            // main output
            System.out.println(cnt);

        }

    }


    private static double getDistance(int x, int y, int x1, int y1){
        return Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
    }

}
