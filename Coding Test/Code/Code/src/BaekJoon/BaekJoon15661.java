package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon15661 {
    static int N;
    static int[][] status;
    static int result = Integer.MAX_VALUE;

    static boolean subset[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N 초기화
        N = Integer.parseInt(br.readLine());
        // 능력치 2차원 배열 초기화
        status = new int[N][N];

        // 부분집합 subset 초기화
        subset = new boolean[N];

        // 능력치 입력
        StringTokenizer st;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeTeam(0);


        // 출력
        System.out.println(result);
    }

    static void makeTeam(int index){
        // 팀이 결성되는 경우
        if(N==index){
            int start = 0;
            int link = 0;

            // start, link팀의 총 능력치를 구한다.
            start = statusTeam(true);
            link = statusTeam(false);

            // 각 팀의 status의 차이를 result에 업데이트 한다.
            compareStatus(start, link);

            // 종료
            return;
        }

        // 팀이 결성되지 않은 경우 팀을 결성한다.
        // index가 start 팀인 경우
        subset[index] = true;
        makeTeam(index+1);

        // index가 link 팀인 경우
        subset[index] = false;
        makeTeam(index+1);

    }

    static int statusTeam(boolean team){
        int statusSum=0;
        for(int i = 0; i< N; i++){
            for(int j =i+1; j<N; j++){
                // i와 j가 같은 팀인 경우
                if(team != subset[i] || team != subset[j]){
                    continue;
                }
                statusSum += status[i][j] + status[j][i];
            }
        }

        return statusSum;
    }

    static void compareStatus(int start, int link){
        int difference = Math.abs(start-link);
        result = Math.min(result, difference);
    }
}
