package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BaekJoon1967 {

    static int n;
    static Node[] nodes;
    static int finalLength=Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n+1];
        for(int i =1; i<n+1; i++){
            nodes[i] = new Node();
        }

        for(int i =0; i<n-1; i++){
            String[] input = br.readLine().split(" ");
            nodes[Integer.parseInt(input[0])].linkedNode.add(new Vec(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            nodes[Integer.parseInt(input[1])].linkedNode.add(new Vec(Integer.parseInt(input[0]), Integer.parseInt(input[2])));
        }

        // main logic

        for(int i =1; i<=n; i++){
            visited = new boolean[n+1];
            if(!visited[i]){
                dfs(i, 0);
            }
        }

        // output
        System.out.println(finalLength);

    }

    private static void dfs(int index, int length){
        finalLength = Math.max(finalLength, length);

        visited[index] = true;

        for(Vec v : nodes[index].linkedNode){
            if(!visited[v.index]){

                dfs(v.index, v.weight+length);
            }
        }


    }

    private static class Node{
        List<Vec> linkedNode;

        public Node(){
            this.linkedNode = new ArrayList<>();
        }
    }

    private static class Vec{
        int index;
        int weight;
        public Vec(int index, int weight){
            this.index = index;
            this.weight =weight;

        }
    }
}
