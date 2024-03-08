package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1068_2 {
    static int n;
    static int[] parents;
    static int root;
    static int deleteNode;
    static int count;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫번째 Input
        n = Integer.parseInt(br.readLine());

        // 두번째 Input
        parents = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            parents[i] = Integer.parseInt(st.nextToken());
            if(parents[i] == -1){
                root = i;
            }
        }
        // 세번째 input
        deleteNode = Integer.parseInt(br.readLine());

        // 노드 삭제
        deleteNode(deleteNode);

        // 리프노드 카운트
        count = 0;
        visited = new boolean[n];

        // 리프노드 카운트 시작
        countLeaf(root);

        // 출력
        System.out.println(count);


    }

    private static void countLeaf(int node) {
        boolean isLeaf = true;
        // 방문여부 체크
        visited[node] = true;

        // node의 부모노드 인덱스가 삭제 안된 경우
        if(parents[node]!=-2){
            for(int i=0; i<n; i++){
                // 방문 안했으면서, 부모노드가 node인 노드라면
                if(parents[i]==node && visited[i]==false){
                    countLeaf(i);
                    isLeaf = false;
                }
            }
            if(isLeaf) count++;
        }


    }

    private static void deleteNode(int deleteNode) {
        parents[deleteNode]=-2;
        for(int i =0; i<n; i++){
            if(parents[i] == deleteNode){
                deleteNode(i);
            }
        }
    }
}
