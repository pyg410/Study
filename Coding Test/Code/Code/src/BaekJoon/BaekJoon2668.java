package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 문제 --
 * 세로 2줄 가로 N개의 칸으로 이루어진 표가 있음.
 * 첫째 줄엔 1~N까지
 * 둘째 줄엔 1 이상 N이하 정수가 있음
 *
 * 1. 첫째 줄에서 숫자를 몇 개 뽑음
 * 2. 그 뽑힌 정수들이 이루는 집합과 뽑힌 정수들의 바로 밑에있는 둘째 줄 정수들이 이루는 집합이 일치해야함.
 * 단, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성해야함
 *
 * 1~N만큼 반복한다.
 * 1로 진입한다.
 * 1을 방문처리한다.
 *
 * 1을 스택에 넣는다.
 * 1의 인접노드에 진입한다.
 *  인접노드를 방문처리한다.
 *
 * 방문했다면?
 *  리스트에서 있는지 판단한다.
 *      리스트에 있다면?
 *          continue
 *      리스트에 없다면?
 *          방문 처리를 초기화하고 다시 반복한다.
 * 인접노드가 방문하지 않았다면?
 *  (재귀)원소에 연결된 노드로 간다.
 *  인접노드 방문처리한다.
 *
 */
public class BaekJoon2668 {
    static int N;
    static int[] num;
    static boolean[] visited;
    static boolean isCycle;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        visited = new boolean[N+1];
        list = new ArrayList<>();


        for(int i=1; i<=N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        // dfs
        for(int i =1; i<=N; i++){
            visited[i] = true;
            isCycle=false;
            dfs(i, i);
        }

        // output
        System.out.println(list.size());
        Collections.sort(list);
        for(Integer i : list){
            System.out.println(i);
        }



    }

    static void dfs(int start, int target){
        if(visited[num[start]] == false){
            visited[num[start]] = true;
            dfs(num[start], target);
            visited[num[start]]=false;
        }
        if(num[start] == target) isCycle = true;
        if(isCycle==true) list.add(start);
    }
}
