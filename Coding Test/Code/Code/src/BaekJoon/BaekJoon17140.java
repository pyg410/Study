package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon17140 {

    static int[][] arr;
    static int r, c, k;
    static int rowLength =3, colLength = 3;
    static int result = -1;


    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr = new int[101][101];

        for(int i =1; i<=3; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // main logic
        operation();


        // output
        System.out.println(result);


    }

    private static void operation() {
        for(int t = 0; t<=100; t++){
            if(arr[r][c] == k){
                result = t;
                break;
            }
            if(isR(rowLength, colLength)){
                for(int i=1; i<=rowLength; i++){
                    rOperation(i);
                }
            }
            else{
                for(int i =1; i<=colLength; i++){
                    cOperation(i);
                }
            }
        }
    }

    private static boolean isR(int rowLength, int colLength){
        return rowLength >= colLength;
    }

    private static void rOperation(int rowNum){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();//num, cnt

        for(int i =1; i<=colLength; i++){
            if(arr[rowNum][i] == 0 ) continue;
            map.put(arr[rowNum][i], map.get(arr[rowNum][i])==null? 1 : map.get(arr[rowNum][i])+1);
        }

        // 정렬
        map.forEach((num, cnt) -> pq.add(new Node(num, cnt)));

        // 배열 재배치
        int newColLength=1;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            arr[rowNum][newColLength++] = node.num;
            arr[rowNum][newColLength++] = node.cnt;
        }

        // 가로 최대 길이 업데이트
        // 열의 총 개수 == 가로
        colLength = Math.max(colLength, newColLength);

        // 나머지 배열 0으로 업데이트
        while (newColLength <= 99){
            arr[rowNum][newColLength++] = 0;
            arr[rowNum][newColLength++] = 0;
        }

    }

    private static void cOperation(int colNum){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();//num, cnt

        for(int i =1; i<=rowLength; i++){
            if(arr[i][colNum] == 0 ) continue;
            map.put(arr[i][colNum], map.get(arr[i][colNum])==null? 1 : map.get(arr[i][colNum])+1);
        }

        // 정렬
        map.forEach((num, cnt) -> pq.add(new Node(num, cnt)));

        // 배열 재배치
        int newRowLength=1;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            arr[newRowLength++][colNum] = node.num;
            arr[newRowLength++][colNum] = node.cnt;
        }

        // 가로 최대 길이 업데이트
        // 열의 총 개수 == 가로
        rowLength = Math.max(rowLength, newRowLength);

        // 나머지 배열 0으로 업데이트
        while (newRowLength <= 99){
            arr[newRowLength++][colNum] = 0;
            arr[newRowLength++][colNum] = 0;
        }
    }

    static class Node implements Comparable<Node>{
        int num;
        int cnt;

        Node(int num, int cnt){
            this.num=num;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node o) {
            // cnt는 내림차순
            if(this.cnt > o.cnt){
                return 1;
            }else if (this.cnt == o.cnt){
                // cnt 같으면 숫자 순으로 오름차순
                return this.num - o.num;
            }else{
                // 그 외는 변경 없음.
                return -1;
            }
        }
    }

}
