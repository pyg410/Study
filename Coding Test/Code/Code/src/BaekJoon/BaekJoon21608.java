package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon21608 {

    static int N,sum;

    static int[] students, dx = {0, 0 , -1, 1}, dy = {1, -1, 0, 0};// 상하좌우
    static int[][] map;

    static Map<Integer, Set<Integer>> preference;

    public static void main(String[] args) throws IOException{

        // input
        init();

        // main logic
        solution();

        // output
        System.out.println(sum);

    }

    private static void solution(){
        // 학생 자리 배치
        for(int i =0; i<students.length; i++){
            Seat seat = findSeat(students[i]);
            map[seat.x][seat.y] = students[i];
        }

        // 점수 합산
        for(int i = 0; i< N; i++){
            for(int j = 0; j<N; j++){
                // 주변 학생 수에 따라 점수 합산
                int count = getStudentSum(i, j, map[i][j]);

                if(count >0){
                    sum+= (int)Math.pow(10, count -1);
                }

            }
        }
    }

    private static Seat findSeat(int student) {

        Seat seat = null;
        for(int i =0; i<N; i++){
            for(int j = 0; j<N; j++){
                // 자리에 누군가 있다면 스킵
                if(map[i][j] >0) continue;

                // 현재 자리의 정보
                Seat current = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i,j));

                // 비교할 자리가 null이라면 초기화 후 skip
                if(seat == null){
                    seat = current;
                    continue;
                }

                // 이전 좌석과 현재 좌석 비교
                if(seat.compareTo(current) > 0){
                    seat = current;
                }
            }
        }
        return seat;

    }

    private static int getEmptySum(int x, int y) {

        // 빈 칸 수
        int count = 0;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 벗어나면 스킵
            if(nx <0 || nx >= N || ny <0 || ny >=N) continue;

            // 빈칸이면 카운트 증가
            if(map[nx][ny] == 0) count++;
        }

        return count;
    }

    private static int getStudentSum(int x, int y, int student) {

        int count = 0;

        // 인접한 좋아하는 학생 수
        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(0> nx || nx >= N || 0 > ny || ny >= N ) continue;

            // 인접한 학생이 좋아하는 학생에 포함되면 count 증가
            if(preference.get(student).contains(map[nx][ny])) count++;

        }

        return count;
    }

    private static void init()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sum = 0;
        map = new int[N][N];
        students = new int[N*N];
        preference = new HashMap<>();
        for(int i =0; i<N*N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());

            students[i] = student;

            preference.put(student, new HashSet<>());
            for(int j=0; j<4; j++){
                preference.get(student).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    private static class Seat implements Comparable<Seat>{
        int x, y, studentSum, emptySum; // x,y좌표, 좋아하는 학생 수, 주변 빈칸 수

        public Seat(int x, int y, int studentSum, int emptySum){
            this.x=x;
            this.y=y;
            this.studentSum=studentSum;
            this.emptySum=emptySum;
        }


        @Override
        public int compareTo(Seat o) {
            // 인접 좋아하는 학생 수로 비교
            if(studentSum != o.studentSum) return -(studentSum - o.studentSum);

            // 인접 빈칸 수로 비교
            if(emptySum != o.emptySum) return -(emptySum - o.emptySum);

            // 행으로 비교
            if(x != o.x) return x - o.x;

            // 열로 비교
            return y - o.y;
        }
    }
}
