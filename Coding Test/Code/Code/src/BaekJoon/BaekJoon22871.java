package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BaekJoon22871 {

    private static int n;
    private static int[] a;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // main logic
        solution();

    }

    private static void solution() {
        long start = 1;
        long end = (long)(n-1) * (long)(1+ Math.abs(a[n-1]-a[0]));
        long answer = 0;

        while(start<=end){
            long mid = (start+end)/2;

            boolean[] visited = new boolean[n];
            boolean flag = false;

            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            visited[0] = true;


            while(!stack.isEmpty()){
                Integer pop = stack.pop();

                if(pop == n-1){
                    flag = true;
                    break;
                }

                for(int i = pop + 1; i< n; i++){
                    long power = (long)(i-pop) * (long)(1+ Math.abs(a[i]- a[pop]));

                    if(!visited[i] && power <= mid){
                        stack.push(i);
                        visited[i] = true;
                    }
                }
            }

            if(flag){
                answer = mid;
                end = mid -1;
            }else{
                start = mid + 1;
            }
        }

        System.out.println(answer);

    }
}
