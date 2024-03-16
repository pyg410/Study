package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2841 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer>[] queues = new Queue[7];
        for(int i = 0; i<7; i++){
            queues[i] = new LinkedList<>();
        }
        int cnt =0;


        // main logic
        for(int i=0; i<N; i++){
            int line = arr[i][0];
            int fret = arr[i][1];
            boolean isFret = false;

            int q = queues[line].size();
            for(int j = 0; j<q; j++){
                int oldFret = queues[line].poll();
                if(fret == oldFret){// 해당 프렛이 눌려있는경우
                    queues[line].offer(oldFret);
                    isFret = true;
                    break;
                }
                else if(fret > oldFret){
                    queues[line].offer(oldFret);
                    continue;
                }else{//더 작은 경우
                    cnt++;
                }
            }
            if(!isFret){
                cnt++;
                queues[line].offer(fret);
            }
        }

        System.out.println(cnt);


    }
}
