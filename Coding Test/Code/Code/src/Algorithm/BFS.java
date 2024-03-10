package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static int[][] graph = {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};
    // 방문처리를 위한 boolean배열 선언
    static boolean[] visited = new boolean[9];
    // 방문 순서를 저장하는 리스트
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        bfs();

        System.out.print(" start -> ");
        for(Integer i : list){
            System.out.print(i + " -> ");
        }
        System.out.print(" end ");
    }

    static void bfs(){

        Queue<Integer> queue = new LinkedList<>();

        // 1번노드를 큐에 넣는다.
        queue.offer(1);
        // 큐를 방문처리한다.
        visited[1] = true;

        while(!queue.isEmpty()){
            // 큐에서 노드 하나를 뺀다.
            int nodeIndex = queue.poll();
            list.add(nodeIndex);
            // 큐에 가까운 노드들을 탐색한다.
            // 만약 방향에 따라서 주위탐색을 해야한다면 dx와 같은 배열을 통해 4번 반복해주면 된다.
            for(int i =0; i<graph[nodeIndex].length; i++){
                // 가까운 노드를 하나 구한다.
                int temp = graph[nodeIndex][i];
                // 가까운 노드를 방문하지 않았는지 판단한다.
                if(!visited[temp]){
                    // 가까운 노드 방문처리.
                    visited[temp] = true;
                    // 가까운 노드를 큐에 넣는다.
                    queue.offer(temp);

                }
            }
            // 가까운 노드 방문처리가 끝났다.
            // 여기서 이미 1번노드는 빠져있고 큐에 들어있는 노드들은 가까운 노드들이다.
        }
    }
}
