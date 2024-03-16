package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -- 문제
 * Si에서 시작 - Ti에 종료되는 N개의 수업.
 * 최소의 강의실 사용
 * 모든 수업 가능해야함.
 * 수업이 끝난 직후 다음 수업 가능.
 *
 * -- 입력
 * N(1이상 200_000 이하)
 * Si Ti의 목록이 주어진다.(0 이상 10^9 이하)
 *
 * -- 출력
 * 강의실의 개수 출력
 *
 * -- 풀이
 * 시간의 길이에 따라 정렬해야하나?
 * 1번강의실에 시간이 가장 긴것을 둔다.
 * 남는 시간대에 맞는 시간을 구한다.
 * 브루트 포스로 구하기에는 N이 너무 크다.(시간제한이 1초임)
 * 일단, 시작시간으로 정렬을 한다.
 * 시작시간이 같다면 종료시간으로 비교한다.
 *
 * 정렬된 데이터를 순회하면서, 가장 빠르게 시작하고 가장 빠르게 종료하는 강의 다음에 들을 수 있는 강의를 찾으면 될듯?
 *
 *
 */

public class BaekJoon11000 {



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Lecture> list = new ArrayList<>();

        StringTokenizer st;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);
        // 우선순위 큐
        Queue<Integer> q = new PriorityQueue<>();
        int endTime = 0;
        for(Lecture lecture : list){
            endTime = lecture.end;

            // 큐가 비어있지 않고, 지금 탐색하는 강의의 시작시간보다 종료시간이 더 이전인 경우
            if(!q.isEmpty() && q.peek() <= lecture.start){
                q.poll();
            }
            q.add(endTime);
        }

        System.out.println(q.size());

    }

    static class Lecture implements Comparable<Lecture>{
        int start, end;

        public Lecture(int start, int end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.start == o.start){
                // 시작시간이 같으면 종료시간으로 비교
                return this.end - o.end;
            }// 아닌 경우 시작시간으로 비교
            else{
                return this.start - o.start;
            }
        }
    }
}
