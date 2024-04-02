package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon14502 {
    static int[][] graph;
    static int result = Integer.MIN_VALUE;
    static int[] dx = {0,0,-1,1};//상하좌우
    static int[] dy = {1,-1,0,0};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        graph = new int[n][m];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(0);

        System.out.println(result);


    }

    // 벽을 세운다
    static void dfs(int wall){
        if(wall == 3){
            bfs();
            return;
        }

        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                if(graph[i][j] == 0){
                    graph[i][j] = 1;
                    dfs(wall+1);
                    graph[i][j] = 0;
                }
            }
        }

    }
    // 바이러스를 채운다.
    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                if(graph[i][j]==2){
                    queue.offer(new Node(i, j));
                }
            }
        }

        int[][] copyGraph = new int[n][m];
        for(int i=0; i<n; i++){
            copyGraph[i] = graph[i].clone();
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(0<=nx && nx <n && 0<= ny && ny < m && copyGraph[nx][ny]==0){
                    queue.offer(new Node(nx, ny));
                    copyGraph[nx][ny]=2;
                }
            }
        }

        // 넓이를 구해준다.
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                if(copyGraph[i][j]==0){
                    cnt++;
                }
            }
        }
        result = Math.max(result, cnt);

    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x =x;
            this.y=y;
        }
    }
}

