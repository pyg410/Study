package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon1927 {

    private static PriorityQueue<Integer> pq;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        // main logic
        pq = new PriorityQueue<>();
        for(int i=0; i<testcase; i++){
            operation(Integer.parseInt(br.readLine()));
        }

        // output
        System.out.println(sb);
    }

    private static void operation(int command){
        if(command == 0){
            if(pq.isEmpty()) {
                sb.append(0).append("\n");
                return;
            }
            sb.append(pq.poll()).append("\n");
            return;
        }
        pq.offer(command);

    }
}
