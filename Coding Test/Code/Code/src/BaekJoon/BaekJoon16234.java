package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon16234 {

    static int N;
    static int L;
    static int R;
    static int[][] graph;
    static int cnt = 0;
    static boolean[][] visit;
    static ArrayList<Node> list;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // n, l, r input
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // graph input
        graph = new int[N][N];
        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = move();

        System.out.println(cnt);
    }

    static int move(){

        int result = 0;
        // 인구이동
        while(true){
            boolean isMove = false;

            visit = new boolean[N][N];
            // 모든 노드를 순회한다.
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    // 방문하지 않았다면
                    if(!visit[i][j]){
                        // bfs를 통해 주위 노드를 돈다.
                        int sum = bfs(i, j);
                        if(list.size()>1){
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove){
                return result;
            }
            result++;

        }


    }


    static int bfs(int x, int y){
        // 노드 저장할 큐
        Queue<Node> queue = new LinkedList<>();
        // 방문한 노드들을 저장하기 위한 리스트
        list = new ArrayList<>();

        // 큐와 리스트에 현재 노드를 저장한다.
        queue.offer(new Node(x, y));
        list.add(new Node(x,y));


        // 노드 방문여부를 true로 바꾼다.
        visit[x][y] = true;
        // 해당 노드의 값을 sum에 저장한다.
        int sum = graph[x][y];
        // 큐가 빌때까지 돈다.
        while(!queue.isEmpty()){
            // 큐에서 하나 뺀다. -> 처음에는 현재 노드를 빼고 그 후에는 이동한 노드를 빼게 된다.
            Node current = queue.poll();

            // 큐의 4방향으로 체크한다.(인접한 노드를 방문한다.)
            for(int i=0; i<4; i++){
                // 다음을모 이동할 x, y좌표를 초기화 한다.
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                // 방향에 문제가 없다면?
                // 노드가 ArrayOutOfIndex 오류에 해당하지 않고, 방문하지 않은 노드여야 한다.
                if( nx>=0 && ny>=0 && nx < N && ny <N && !visit[nx][ny]){
                    // 다음 노드와 현재 노드 차이의 절대값 구한다.
                    int diff = Math.abs(graph[current.x][current.y] - graph[nx][ny]);
                    // 만약, 절대값이 L이상 R 이하라면 방문이 가능하다.
                    if(L <= diff && R >= diff){
                        // 큐에 해당 노드를 넣는다.
                        queue.offer(new Node(nx, ny));
                        // 리스트에 해당 노드를 넣는다.
                        list.add(new Node(nx, ny));
                        // 합에 해당 값을 추가한다
                        sum+=graph[nx][ny];
                        // 방문처리를 한다.
                        visit[nx][ny] = true;

                    }
                }
            }

        }
        return sum;
    }

    public static void changePopulation(int sum){

        int avg = sum / list.size();
        for(Node n : list){
            graph[n.x][n.y] = avg;
        }
    }

    static class Node{
        private final int x;
        private final int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
