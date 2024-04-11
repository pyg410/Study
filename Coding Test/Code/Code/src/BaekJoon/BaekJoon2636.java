package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon2636 {

    static int h;
    static int w;
    static int[][] cheese;
    static boolean[][] visited;
    static int time;
    static int cnt;
    static boolean hasNotCheese;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        String[] input = br.readLine().split(" ");
        h = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);
        cheese = new int[h][w];
        visited = new boolean[h][w];
        for(int i=0; i<h; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<w; j++){
                cheese[i][j] = Integer.parseInt(input[j]);
            }
        }


        // main logic

        time = 0;
        while(true){
            if(hasNotCheese){
                break;
            }
            cnt = 0;
            for(int i =0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(cheese[i][j]==1){
                        cnt++;
                    }
                }
            }
            hasNotCheese = true;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(cheese[i][j]==1 && !visited[i][j]){
                        bfs(i,j);
                        hasNotCheese=false;
                    }
                }
            }

            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    System.out.print(cheese[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("-------");

            time++;

        }

        System.out.println(time);
        System.out.println(cnt);

    }

    static void bfs(int height, int weight){
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(height, weight));
        visited[height][weight] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i=0; i<4; i++){
                int ny = node.h + dy[i];
                int nx = node.w + dx[i];

                if(0<= nx && nx < w && 0<= ny && ny < h && !visited[ny][nx]){
                    if(cheese[ny][nx] == 1){
                        queue.offer(new Node(ny, nx));
                        visited[ny][nx] = true;
                    }else {
                        cheese[node.h][node.w]= 0;
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
