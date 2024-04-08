package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon19598 {

    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];



        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 종료시간 넣을 우선순위 큐

        for(int i=0; i<n; i++){

            if(pq.isEmpty()){
                pq.offer(arr[i][1]);
            }else{
                if(pq.peek() <= arr[i][0]){
                    pq.poll();
                    pq.offer(arr[i][1]);
                }else{
                    pq.offer(arr[i][1]);
                }
            }
        }

        System.out.println(pq.size());

    }

}
