package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon2573_2 {
    static boolean[][] visited;
    static boolean[][] iceBurgVisited;
    static int[][] graph;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int n;
    static int m;



    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        graph = new int[n][m];



        for(int i=0; i<n; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }

        // main logic
        int time = 0;

        while(true){
            int cnt = checkIceBurgNum();
            if(cnt >= 2){

            } else if (cnt == 0) {
                time = 0;
                break;
            }

            // 빙산 녹이기
            bfs();
            time ++; // 1년 지남
        }
        System.out.println(time);

    }

    public static void bfs() {
        Queue<Ice> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) {
                    q.add(new Ice(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Ice ice = q.poll();

            int sea = 0; // 빙산 상하좌우에 존재하는 바닷물 영역의 개수

            for (int i = 0; i < 4; i++) {
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && graph[nx][ny] == 0) {
                        sea++;
                    }
                }
            }

            if (graph[ice.x][ice.y] - sea < 0) {
                graph[ice.x][ice.y] = 0; // 각 칸에 저장된 높이는 0보다 더 줄어들지 않기 때문에 0보다 작아지는 경우 0을 저장
            } else {
                graph[ice.x][ice.y] -= sea;
            }
        }
    }

    private static int checkIceBurgNum() {
        int cnt= 0;
        visited = new boolean[n][m];
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(graph[i][j]>0 && !visited[i][j]){
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }



    static void bfs(int x, int y){
        Queue<BaekJoon2573.Ice> queue = new LinkedList<>();

        queue.offer(new BaekJoon2573.Ice(x, y));
        visited[x][y]=true;



        while(!queue.isEmpty()){
            BaekJoon2573.Ice ice = queue.poll();

            for(int i=0; i<4; i++){
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if(0<=nx && nx < n && 0<=ny && ny <m && !visited[nx][ny]){
                    if(graph[nx][ny]>0){
                        visited[nx][ny]=true;
                        queue.offer(new BaekJoon2573.Ice(nx,ny));
                    }
                }

            }

        }


    }

    static class Ice{
        int x;
        int y;
        public Ice(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
