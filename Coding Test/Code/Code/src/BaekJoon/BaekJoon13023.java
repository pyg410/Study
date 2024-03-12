package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon13023 {

    static int N;
    static int M;
    static boolean[] visited;
    static boolean isABCDE;
    static List<Integer>[] people;
    static int depth =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        people = new ArrayList[N];

        for(int i=0; i<N; i++){
            people[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            people[a].add(b);
            people[b].add(a);
        }


        for(int i=0; i<N; i++){
            visited = new boolean[N];
            depth++;
            dfs(i);
            depth--;
            if(isABCDE){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);




    }

    static void dfs(int node){
        if(depth>=5) {
            isABCDE=true;
            return;
        }
        visited[node]=true;
        for(int i =0; i<people[node].size(); i++){
            if(visited[people[node].get(i)] == false){
                depth++;
                dfs(people[node].get(i));
                depth--;
            }
        }
        visited[node]=false;
    }

}
