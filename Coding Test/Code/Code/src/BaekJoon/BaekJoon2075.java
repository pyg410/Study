package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BaekJoon2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());

        for(int i =0; i<n; i++){
            String[] arr = br.readLine().split(" ");
            for(int j =0; j<n; j++){
                priorityQueueHighest.offer(Integer.parseInt(arr[j]));
            }
        }


        for(int i =0; i<n-1; i++){
            priorityQueueHighest.poll();
        }
        System.out.println(priorityQueueHighest.poll());

    }
}
