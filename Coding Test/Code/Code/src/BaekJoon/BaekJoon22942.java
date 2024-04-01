package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon22942 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Circle> pq = new PriorityQueue<>();

        for(int i =0; i<N; i++){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);

            pq.offer(new Circle(x, r));
        }

        Circle preCircle = pq.poll();
        int preX = preCircle.x;
        int preR = preCircle.r;

        while(!pq.isEmpty()){

            int x = pq.peek().x;
            int r = pq.peek().r;
            // 만나지 않는 경우
            if(r+preR<Math.abs(x-preX) || Math.abs(x-preX)< Math.abs(r-preR) || (Math.abs(x-preX) == 0)) {
                preCircle = pq.poll();
                preX = preCircle.x;
                preR = preCircle.r;
            } else{
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }

     static class Circle implements Comparable<Circle> {

        int x;
        int r;

        public Circle(int x, int r){
            this.x = x;
            this.r = r;
        }

         //         @Override
//         public int compareTo(Circle o) {
//            if(this.r == o.r){
//                return this.x - o.x; // 반지름 같으면 중심기준 오름차순 정렬
//            }
//             return o.r - this.r; // 반지름 내림차순 정렬
//         }
         @Override
         public int compareTo(Circle o) {
             if(this.r ==o.r)
             {
                 return this.x-o.x;
             }
             else
                 return o.r- this.r;
         }

     }
}
