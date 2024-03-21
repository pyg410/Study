package BaekJoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon5547 {

    static int w, h;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dw1 = {0, 1, -1, 1, 0, 1};
    static int[] dh1 = {-1, -1, 0, 0, 1, 1};
    static int[] dw2 = {-1, 0, -1, 1, -1, 0};
    static int[] dh2 = {-1, -1, 0, 0, 1, 1};

    static int result = 0;



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        graph = new int[h+1][w+1];
        visited = new boolean[h+1][w+1];

        for(int i=1; i<=h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=w; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i =1; i<=h; i++){
            for(int j=1; j<=w; j++){
                if(!visited[i][j] && graph[i][j] == 1){
                    dfs(j,i);
                }
            }
        }

        for(int i=1; i<=h; i++){
            for(int j =1; j<=w; j++){
                if(graph[i][j] == 0){
                    inner(j, i);
                }

            }
        }

        System.out.println(result);

    }

    private static void inner(int w, int h) {
            int cnt=0;
            for(int k =0; k<6; k++){
                int nx, ny;
                if(h /2 !=0){// 홀수
                    nx = w+ dw1[k];
                    ny = h+ dh1[k];

                }else {// 짝수
                    nx = w+ dw2[k];
                    ny = h+ dh2[k];
                }
                if(0< nx && nx <= w && 0< ny && ny<= h && graph[ny][nx]==1){
                    cnt++;
                }

            }
            if(cnt >=6){
                result -=6;
                System.out.println("예이~");
            }


    }

    static void dfs(int w, int h){

        int cnt = 0;
        visited[h][w] = true;

        for(int i = 0; i<6; i++){
            int nx, ny;
            if(h /2 !=0){// 홀수
                nx = w+ dw1[i];
                ny = h+ dh1[i];

            }else {// 짝수
                nx = w+ dw2[i];
                ny = h+ dh2[i];
            }

            if(0< nx && nx <= w && 0< ny && ny<= h && graph[ny][nx]==1){
                cnt++;
                if(!visited[ny][nx]){
                    dfs(nx,ny);
                }

            }
        }
        System.out.println(w + ", " + h+"/ cnt : " + cnt);
        result += 6-cnt;

    }
}
