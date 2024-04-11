package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon11279 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2-o1));

        // main logic

        for(int i =0; i<n; i++){
            int cmd = Integer.parseInt(br.readLine());
            if(cmd == 0){
                if(!pq.isEmpty()){
                    System.out.println(pq.peek());
                    pq.poll();
                }else{
                    System.out.println(0);
                }
            }else{
                pq.offer(cmd);
            }

        }


    }
}
