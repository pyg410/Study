package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * -- 문제
 * 용사의 처음 위치 (1,1)
 * 성 크기 (n,m)
 * 성 내에는 벽이 있다.
 * 공주의 위치 (n,m)
 *
 * 시간제한 : T시간
 * 한 칸 이동 시간 : 1시간
 * 용사의 이동방향 : 상하좌우
 *
 * 특별조건 : 명검 그람
 * 그람이 있는 경우, 벽을 부수고 갈 수 있다.
 *
 * -- 입력
 * N M T
 * N,M : 3이상 100 이하
 * T : 1이상 10000이하
 * 성의 구조
 * 0 : 이동가능
 * 1 : 벽
 * 2 : 그람
 *
 * -- 출력
 * T시간 내 구할 수 있는 경우 : 최단경로 출력
 * 구할 수 없는 경우 : Fail
 *
 * -- 풀이
 * 크게 2가지 경우의 수로 나눠서 풀어야 한다.
 * 0. 최종 경로 시간은 항상 T이하여야 한다.
 * 1. 용사 -> 공주 : 그람을 구하지 않고, 단순하게 bfs로 가는 경우(최단경로이므로)
 * 2. 용사 -> 그람 -> 공주 : 그람까지 bfs로 최단경로로 간 후, 그람의 위치부터 공주의 위치까지 일직선으로 가는 경우
 *
 * 엣지 케이스
 * 1. 공주가 있는 벽이 사방으로 막힌 경우
 * T시간 내에 못찾으면 fail
 * 만약 bfs로 경로를 찾지 못하면 Fail
 * 2. 그람이 있는 벽이 사방으로 막힌 경우
 * 1과 동일
 * 3. T시간내에 찾지 못하는 경우
 * T시간내에 못찾으면 fail
 * 4. 벽의 위치가 index범위를 넘어서는 경우
 * Nx, ny가 0이하인지, n,m초과인지 판단하는 조건문을 추가한다.
 *
 * dx = {0, 0, -1, 1} (상, 하, 좌, 우)
 * dy = {1, -1, 0, 0} (상, 하, 좌, 우)
 * visited[][] = new boolean[n+1][m+1];
 *
 * 1. 큐에 용사 현재 위치를 넣는다.
 * 2. 용사 현재 위치를 방문처리한다.
 * 큐가 빌 때까지 반복
 * 0. 현재 cnt가 t를 넘었으면 false리턴
 * 0. 용사 현재 위치의 노드가 2라면 직선뚫기 메서드 리턴
 * 0. 해당 위치가 N,M이면 true 리턴
 * 3. 큐에서 노드 하나를 꺼낸다.
 * 4. cnt++;
 * 5. 해당 노드의 상하좌우 노드를 큐에 넣는다.(단, 상하좌우 노드는 index범위를 넘으면 안됨, 해당 노드가 1이면 안됨. 방문하지 않아야함. 반복)
 * 6. 해당 노드를 방문처리 한다.
 * 큐가 비었다면 종료
 * 7. false를 리턴한다.
 *
 */
public class BaekJoon17836 {
    static int n,m,t;
    static int gramCnt = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] cnt;
    static boolean[][] visited;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static boolean status;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];
        cnt = new int[n+1][m+1];
        cnt[n][m] = Integer.MAX_VALUE;

        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1; j<=m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        status = bfs(new Node(1,1));

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                System.out.print(cnt[i][j] + " ");
            }
            System.out.println();
        }

        if(status){
            System.out.println(cnt[n][m]);
        }else {
            System.out.println("Fail");
        }



    }
// * 1. 큐에 용사 현재 위치를 넣는다.
// * 2. 용사 현재 위치를 방문처리한다.
//            * 큐가 빌 때까지 반복
// * 0. 현재 cnt가 t를 넘었으면 false리턴
// * 0. 용사 현재 위치의 노드가 2라면 직선뚫기 메서드 리턴
// * 0. 해당 위치가 N,M이면 true 리턴
// * 3. 큐에서 노드 하나를 꺼낸다.
//            * 4. cnt++;
// * 5. 해당 노드의 상하좌우 노드를 큐에 넣는다.(단, 상하좌우 노드는 index범위를 넘으면 안됨, 해당 노드가 1이면 안됨. 방문하지 않아야함. 반복)
//            * 6. 해당 노드를 방문처리 한다.
//            * 큐가 비었다면 종료
// * 7. false를 리턴한다.
    static boolean bfs(Node node){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.x][node.y] = true;

        while (!queue.isEmpty()){
            Node pollNode = queue.poll();

            if(pollNode.x == n && pollNode.y==m){
                return true;
            }
            if(map[pollNode.x][pollNode.y]==2){
                gram(pollNode);
            }
            for(int i=0; i<4; i++){
                int nx = pollNode.x + dx[i];
                int ny = pollNode.y + dy[i];
                if(nx >0 && nx <= n && ny>0 && ny<=m && map[nx][ny]!=1 && !visited[nx][ny]){
                    visited[nx][ny] =true;
                    if(nx ==n && ny ==m){
                        cnt[nx][ny] = Math.min(cnt[pollNode.x][pollNode.y]+1, cnt[nx][ny]);
                        queue.offer(new Node(nx, ny));
                    }else{
                        cnt[nx][ny] = cnt[pollNode.x][pollNode.y]+1;
                        queue.offer(new Node(nx, ny));
                    }

                }
            }


        }
        return false;
    }

    private static void gram(Node node) {
        // 2,6 -> 6,6까지 간다면 6-2 + 6-6 = 4
        cnt[n][m] = cnt[node.x][node.y] + Math.abs(node.x-n) + Math.abs(node.y-m);
    }

    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
