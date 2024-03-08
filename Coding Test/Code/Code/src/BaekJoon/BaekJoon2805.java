package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon2805 {

    public static void main(String args[])throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int treeNum = Integer.parseInt(st.nextToken()); // 존재하는 나무 개수
        int treeToTake = Integer.parseInt(st.nextToken()); // 가져갈 나무 길이
        int[] tree = new int[treeNum]; // 각 나무 길이를 원소로 갖는 배열


        st = new StringTokenizer(br.readLine());

        for(int i =0; i<treeNum; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        // proceeds

        // 나무를 오름차순 정렬한다.    
        Arrays.sort(tree);

        int min = 0;
        int max = tree[tree.length-1];

        while(min +1 < max){
            int median = (min+max)/2;

            boolean check = check(tree, median, treeToTake);

            if(check){
                min = median;
            }else{
                max = median;
            }

        }
        System.out.println(min);
    }

    private static boolean check(int[] tree, int median, int target) {
        long sum = 0;
        for(int idx = 0; idx< tree.length; idx++){
            // 만약 (나무길이 - median > 0) 이라면 sum에 더한다.
            if(tree[idx] - median > 0){
                sum+= tree[idx]- median;
            }
        }
        return sum >= target;
    }

}