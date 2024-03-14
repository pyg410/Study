package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1992 {
    static int[][] img;
    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        img = new int[N][N];

        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                img[i][j] = line.charAt(j) - '0';
            }
        }

        divideConquer(new Node(0, 0), N);
        System.out.println(sb);

    }

    static void divideConquer(Node node, int size){
        if(canZip(node.x, node.y, size)){
            sb.append(img[node.x][node.y]);
            return;
        }

        int s = size/2;

        sb.append('(');

        // 왼쪽위
        divideConquer(new Node(node.x, node.y), s);
        // 오른쪽위
        divideConquer(new Node(node.x, node.y + s), s);
        // 왼쪽아래
        divideConquer(new Node(node.x + s, node.y), s);
        // 오른쪽아래
        divideConquer(new Node(node.x + s, node.y + s), s);

        sb.append(')');

    }

    private static boolean canZip(int x, int y, int size) {
        int value = img[x][y];

        for(int i =x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(value != img[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x =x;
            this.y =y;
        }
    }
}
