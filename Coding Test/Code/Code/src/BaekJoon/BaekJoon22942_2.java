package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon22942_2 {

    static int N;
    static boolean flag = false;
    static StringTokenizer st;

    static class Checker implements Comparable<Checker>{
        int no;
        int dot;

        public Checker(int no, int dot) {
            super();
            this.no = no;
            this.dot = dot;
        }

        @Override
        public int compareTo(Checker o) {
            if(this.dot == o.dot) flag = true;    // 같은 위치에 점이 존재한다
            return this.dot - o.dot;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Checker> pq = new PriorityQueue<>();
        Stack<Integer> stack = new Stack<>();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            pq.add(new Checker(i,x-r));
            pq.add(new Checker(i,x+r));
        }

        if(flag) {
            System.out.println("NO");
            return;
        }

        while(!pq.isEmpty()) {
            if(stack.isEmpty()) {
                stack.push(pq.poll().no);
            }
            else {
                int number = pq.poll().no;
                if(stack.peek() == number) {    // 번호와 스택의 Top이 같다면 스택에서 뺀다
                    stack.pop();
                }
                else{
                    stack.push(number);        // 다르다면 스택에 넣는다.
                }
            }
        }

        if(stack.isEmpty()) {            // 스택이 비어있다 == 모두 쌍이 맞다.
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }

    }
}