package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1697 {

    private static int subin;
    private static int bro;
    private static boolean[] visited;
    private static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new boolean[100_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        subin = Integer.parseInt(st.nextToken());
        bro = Integer.parseInt(st.nextToken());

        // main logic
        bfs();

        // output
        System.out.println(result);



    }

    private static void bfs(){

        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(subin, 0));
        visited[subin] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.location == bro){
                result = Math.min(result, node.cnt);
            }

            int nextLocation = node.location + 1;
            if(nextLocation >=0 && nextLocation <=100_000 && !visited[nextLocation]){
                visited[nextLocation] = true;
                queue.offer(new Node(nextLocation, node.cnt+1));
            }

            nextLocation = node.location-1;
            if(nextLocation >=0 && nextLocation <=100_000&& !visited[nextLocation]){
                visited[nextLocation] = true;
                queue.offer(new Node(nextLocation, node.cnt+1));
            }

            nextLocation = node.location*2;
            if(nextLocation >=0 && nextLocation <=100_000&& !visited[nextLocation]){
                visited[nextLocation] = true;
                queue.offer(new Node(nextLocation, node.cnt+1));
            }
        }

    }

    static class Node{
        int location;
        int cnt;

        public Node(int location, int cnt){
            this.location=location;
            this.cnt=cnt;
        }
    }
}
