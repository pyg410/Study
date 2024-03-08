package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1068 {

    static int[][] graph;
    static boolean[] visited;
    static boolean[] result;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째줄 input
        int N = Integer.parseInt(br.readLine());

        // 둘째 줄 input
        StringTokenizer st = new StringTokenizer(br.readLine());
        parents = new int[N];
        for(int i =0; i<N; i++){
            parents[i] = Integer.parseInt(st.nextToken());
        }

        // 셋째 줄 Input
        int deleteNodeIndex = Integer.parseInt(br.readLine());

        // 노드 삭제
        for(int i = 0; i< parents.length; i++){
            if(i == deleteNodeIndex){
                parents[i] = -2;
                continue;
            }
            if(parents[i] == deleteNodeIndex){
                parents[i] = -2;
            }
        }

        // 방문 여부 배열 생성
        visited = new boolean[N];

        // result 배열 생성
        result = new boolean[N];

        // graph 생성
        graph = new int[N][];
        for(int i=0; i<N; i++){
            //여기서 포기..
        }

        dfs(0);
    }

    static void dfs(int nodeIndex){
        // 방문처리
        visited[nodeIndex]=true;

        // 리프 노드 개수찾기
        for(int p : parents){
            // 삭제된 노드
            if(parents[nodeIndex] == -2){
                result[nodeIndex]=false;
            }
            // 루트 노드
            else if(parents[nodeIndex] == -1){

            }
            // 일반 노드
            else{

            }
        }
    }
}
