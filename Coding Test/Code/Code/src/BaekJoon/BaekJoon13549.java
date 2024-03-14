package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon13549 {
    static int max = 100_000;
    static int min=Integer.MAX_VALUE;
    static boolean[] visited;
    static int n,k;
    static Queue<Node> queue;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        visited = new boolean[max+1];
        queue = new LinkedList<>();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(min);

    }

    private static void bfs() {
        queue.offer(new Node(n, 0));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            visited[node.x] = true;

            if(node.x == k){
                min = Math.min(min, node.time);
            }

            if(node.x *2 <=max && !visited[node.x *2]){
                queue.offer(new Node(node.x *2, node.time));
            }
            if(node.x-1 >= 0 && !visited[node.x -1]){
                queue.offer(new Node(node.x -1, node.time+1));
            }
            if (node.x+1 <=max && !visited[node.x+1]){
                queue.offer(new Node(node.x +1, node.time+1));
            }
        }


    }

    static class Node{
        int time;
        int x;

        public Node(int x, int time){
            this.x =x;
            this.time=time;
        }
    }
}
