package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon2841_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        Stack<Integer>[] stacks = new Stack[7];
        for(int i=0; i<7; i++){
            stacks[i] = new Stack<>();
        }
        int cnt = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());


            // 스택이 비어있지 않고, 스택 가장 위의 값이 fret보다 클때
            while(!stacks[line].isEmpty() && stacks[line].peek() > fret){
                // 스택에서 원소 뺀다.
                stacks[line].pop();
                // cnt ++
                cnt++;
            }

            // 스택이 비어있는 경우
            if(stacks[line].isEmpty()){
                stacks[line].push(fret);
                cnt++;
            } else if (stacks[line].peek() < fret) {
                // 스택이 비어있지 않고, 가장 위의 원소가 프렛보다 작은 경우
                stacks[line].push(fret);
                cnt++;
            }
            // 나머지는 같은 경우이므로 그냥 넘어간다.
        }

        System.out.println(cnt);

    }
}