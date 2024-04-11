package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon2636_2 {
    static int h;
    static int w;
    static boolean[][] visited;
    static int[][] cheese;
    static int time=0;
    static int cheeseCnt;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        h = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);
        cheese = new int[h][w];

        for(int i=0; i<h; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<w; j++){
                cheese[i][j] = Integer.parseInt(input[j]);
                if(cheese[i][j]==1) cheeseCnt++;
            }
        }

        // main logic
        int cnt = 0;
        while(cheeseCnt>0){// 남은 치즈가 구해지면 종료
            cnt = cheeseCnt;
            time++;
            visited = new boolean[h][w];
            bfs();

        }

        System.out.println(time);
        System.out.println(cnt);


    }

    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(int i = 0; i<4; i++){
                int ny = node.h + dy[i];
                int nx = node.w + dx[i];

                if(0<=nx && nx<w && 0<= ny && ny <h && !visited[ny][nx]){
                    visited[ny][nx] = true;
                    if(cheese[ny][nx] == 0){
                        queue.offer(new Node(ny, nx));
                    }else{
                        cheeseCnt--;
                        cheese[ny][nx] = 0;
                    }

                }
            }

        }
    }

    static class Node{
        int h;
        int w;
        public Node(int h, int w){
            this.h = h;
            this.w = w;
        }
    }
}
