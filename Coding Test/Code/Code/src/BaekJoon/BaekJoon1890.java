package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1890 {

    static int n;
    static int[][] graph;
    static int cnt = 0;
    static int[] dx = {1,0};// 우, 하
    static int[] dy = {0,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }

        visited[0][0]=true;
        dfs(0,0);

        System.out.println(cnt);

    }

    private static void dfs(int y, int x){

        if(x == n-1 && y == n-1){
            cnt++;
            return;
        }

        for(int i =0; i<2; i++){
            int nx = x + (dx[i]*graph[y][x]);
            int ny = y + (dy[i]*graph[y][x]);

            if(0<= nx && nx <n && 0<=ny && ny < n && !visited[ny][nx]){
                visited[ny][nx]=true;
                dfs(ny,nx);
                visited[ny][nx]=false;
            }
        }


    }
}
