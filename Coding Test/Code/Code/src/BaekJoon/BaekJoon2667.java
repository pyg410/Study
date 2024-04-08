package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2667 {

    static int n;
    static boolean[][] visited;
    static int[][] graph;

    static int[] dx = {0,0,-1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        graph = new int[n][n];

        for(int i=0; i<n; i++){
            String[] st = br.readLine().split("");
            for(int j =0; j<n; j++){
                graph[i][j] = Integer.parseInt(st[j]);
            }
        }

        // main logic
        // visited가 있는지 판단.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                // 집이 있고, 방문하지 않은 경우 bfs
                if(graph[i][j] == 1 && !visited[i][j]){
                    priorityQueue.offer(bfs(i, j));
                }
            }
        }

        // output
        System.out.println(priorityQueue.size());
        while(!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }


    }

    static int bfs(int x, int y){
        Queue<Node> queue = new LinkedList<>();
        int cnt = 0;

        visited[x][y] = true;
        queue.offer(new Node(x, y));
        cnt++;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(int i =0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(0<= nx && nx <n && 0<= ny && ny <n && !visited[nx][ny] && graph[nx][ny] == 1){
                    queue.offer(new Node(nx,ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
