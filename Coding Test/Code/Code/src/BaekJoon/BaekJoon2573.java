package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon2573 {
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

        int cnt = 0;
        int time = 0;
        // 빙산 개수 체크 0년째
        visited = new boolean[n][m];
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(!visited[i][j] && graph[i][j]>0){
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        if(cnt>=2){
            System.out.println(time);
            return;
        }
        while(cnt < 2){

            // 빙산녹이기
            iceBurgVisited = new boolean[n][m];
            for(int i =0; i<n; i++){
                for(int j =0; j<m; j++){
                    if(graph[i][j]==0 && !iceBurgVisited[i][j]){ // 바닷물 탐색
                        meltingIceburg(i, j);
                    }
                }
            }
            time ++; // 1년 지남

            // 빙산 개수 체크
            cnt = 0;
            visited = new boolean[n][m];
            for(int i =0; i<n; i++){
                for(int j =0; j<m; j++){
                    if(!visited[i][j] && graph[i][j]>0){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            if(cnt == 0){
                time=0;
                break;
            }

            for(int i =0; i<n; i++){
                for(int j =0; j<m; j++){
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(time+"년");
            System.out.println("빙산개수 : " + cnt);
            System.out.println("---");


        }

        System.out.println(time);



    }

    private static void meltingIceburg(int i, int j) {
        for(int k=0; k<4; k++){
            int nx = i +dx[k];
            int ny = j + dy[k];
            if(0<=nx && nx < n && 0<=ny && ny <m){
                if(graph[nx][ny]>0){
                    graph[nx][ny]--;
                    iceBurgVisited[nx][ny]=true;
                }
            }
        }
    }

    static void bfs(int x, int y){
        Queue<Ice> queue = new LinkedList<>();

        queue.offer(new Ice(x, y));
        visited[x][y]=true;



        while(!queue.isEmpty()){
            Ice ice = queue.poll();

            for(int i=0; i<4; i++){
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if(0<=nx && nx < n && 0<=ny && ny <m && !visited[nx][ny]){
                    if(graph[nx][ny]>0){
                        visited[nx][ny]=true;
                        queue.offer(new Ice(nx,ny));
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
