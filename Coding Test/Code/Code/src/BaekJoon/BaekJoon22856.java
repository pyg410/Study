package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BaekJoon22856 {
    static Node[] nodes;
    static boolean[] visited;
    static int n;
    static List<Integer> inOrderStatus;

    static int lastNode;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[100001];
        visited = new boolean[n+1];


        for(int i=1; i<=n; i++){
            String[] input = br.readLine().split(" ");
            int curNode = Integer.parseInt(input[0]);
            int leftNode = Integer.parseInt(input[1]);
            int rightNode = Integer.parseInt(input[2]);

            if(nodes[curNode] == null) nodes[curNode] = new Node(leftNode, rightNode);
            else{
                nodes[curNode].leftIdx = leftNode;
                nodes[curNode].rightIdx = rightNode;
            }

            if(leftNode != -1 ){
                if(nodes[leftNode] == null)nodes[leftNode] = new Node();
                nodes[leftNode].parentIdx = curNode;
            }

            if(rightNode != -1){
                if(nodes[rightNode] == null){
                    nodes[rightNode] = new Node();
                }
                nodes[rightNode].parentIdx = curNode;
            }

        }

        // main logic
        inOrderStatus = new ArrayList<>();
        inOrder(1);
        lastNode = inOrderStatus.get(n-1);
        int cnt = solution(1, 0);

        // output
        System.out.println(cnt);




    }

    private static int solution(int nodeIdx, int cnt) {

        if(nodes[nodeIdx].hasLeft() && !visited[nodes[nodeIdx].leftIdx] ){
            return solution(nodes[nodeIdx].leftIdx, cnt+1);
        }
        else if(nodes[nodeIdx].hasRight() && !visited[nodes[nodeIdx].rightIdx]){
            return solution(nodes[nodeIdx].rightIdx, cnt+1);
        }
        else if(nodeIdx == lastNode){
            return cnt;
        }
        else{
            visited[nodeIdx] =true;
            return solution(nodes[nodeIdx].parentIdx, cnt+1);
        }
    }

    private static class Node{
        int leftIdx;
        int rightIdx;
        int parentIdx = -1;

        public Node(int leftIdx, int rightIdx){
            this.leftIdx=leftIdx;
            this.rightIdx=rightIdx;
        }
        public Node(){
            this.leftIdx=-1;
            this.rightIdx=-1;
        }

        private boolean hasLeft(){

            return leftIdx != -1;
        }

        private boolean hasRight(){
            return rightIdx != -1;
        }
    }

    private static void inOrder(int cur) { // in-order(중위 순회)
        Node curNode = nodes[cur];
        int left = curNode.leftIdx;
        int right = curNode.rightIdx;

        if (left != -1) {
            inOrder(left);
        }

        inOrderStatus.add(cur);

        if (right != -1) {
            inOrder(right);
        }
    }
}
