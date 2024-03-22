package Programmers;
import java.util.*;

class Solution {
    /**
     대기실 5개
     5*5 크기
     맨해튼 거리 : |r1 - r2| + |c1 - c2| 입니다.
     맨해튼 거리가 2 이하로 앉으면 안됨. 즉, 2 초과여야함.
     혹은, 파티션으로 막혀있는 경우 가능

     P, O 가 있는 경우 visited[]를 통해서 방문 체크를 하고, bfs로 돌면서
     P, O를 만난 경우 맨해튼 거리가 2를 초과하는지 체크하면 될듯?
     2를 초과하면 0을 리턴하고, 2를 초과하지 않으면 1를 리턴하면 될듯.

     근데 문제는, visited처리를 해주면 안될 수도..?
     가는 길이 겹칠 수도 있음.
     아니면, 해당 2차원 배열에서, P의 위치만 다 파악한다음에, 이것도 아닌거같아.

     visited처리를 안해주면 어떻게 되지?

     아니면, visited처리를 안해주고, 어짜피 맨해튼 거리 기준으로 2를 초과하는지만 묻는거니까, bfs로 돌되, 2칸만 움직일 수 있게 하는거지..
     2칸 움직였을 때 사람이 없으면 그건 통과니까?
     2칸만 움직였을 때 P가 존재하면, 그것대로 이제 0리턴해주고,
     P가 존재하지 않으면 1리턴해주면 되는..
     잘못생각했다.
     0,0 -> 0,1 로 갔을 때 0,1 -> 0,0로 돌아가는 이상현상이 발생함.
     방문처리는 무조건 해줘야함.
     단, visited를 매번 초기화 해줘야할듯?

     입력 제한
     places[] 5*5 고정



     */
    static int[] dx = {0, 0, -1, 1};//상하좌우
    static int[] dy = {-1, 1, 0, 0};
    static String[][] waitingRoom;
    static final int MANHATTAN_DISTANCE = 2;
    static boolean[][] visited;

    public int[] solution(String[][] places) {

        int[] answer = new int[5];

        for(int i = 0; i<5; i++){

            waitingRoom = new String[5][5];
            for(int j = 0; j<places[i].length; j++){
                String str = places[i][j];
                for(int k=0; k<5; k++){
                    waitingRoom[j][k] = String.valueOf(str.charAt(k));
                }
            }

            int result = 1;
            for(int j =0; j<5; j++){
                for(int k =0; k<5; k++){
                    if(Objects.equals(waitingRoom[j][k], "P")){
                        visited = new boolean[5][5];
                        if(!bfs(j, k)){
                            result = 0;
                        }
                    }
                }
            }


            answer[i] = result;

        }


        return answer;
    }

    private boolean bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(x, y, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            visited[node.x][node.y] = true;
            // 맨해튼 거리 체크
            if(node.cnt > MANHATTAN_DISTANCE){
                continue;
            }else{
                // queue에 집어넣는다. -> 큐를 뺀다. -> 메인로직을 한다. -> 주위 노드를 넣는다(cnt++해서) -> 큐를 뺀다. -> 반복한다.
                if(node.cnt != 0 && waitingRoom[node.x][node.y].equals("P")){
                    // 맨해튼 거리
                    return false;
                }

                for(int i =0; i<4; i++){
                    int nx = dx[i] + node.x;
                    int ny = dy[i] + node.y;
                    // 다음 노드 범위 체크
                    if(0<= nx && nx <5 && 0<=ny && ny<5 && !visited[nx][ny] && !Objects.equals(waitingRoom[nx][ny], "X")){
                        q.offer(new Node(nx, ny, node.cnt+1));
                    }
                }
            }


        }


        return true;
    }

    static public class Node{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt){
            this.x=x;
            this.y=y;
            this.cnt = cnt;
        }

    }
}
