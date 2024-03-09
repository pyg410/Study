package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제--
 * 스티커 2n개
 * 그림(a) 2행 n열
 * 상냥이는 스티커 이용해 책상을 꾸민다.
 * 스티커 떼면 그 스티커와 변을 공유하는 스티커는 모두 찢어짐.
 * 각 스티커에 점수를 부여. 점수의 합이 최대가 되게 스티커를 떼어낸다.
 * 스티커의 점수의 최댓값을 구하는 프로그램을 작성하시오
 * ---
 * 제한조건--
 * 시간 : 1초
 * 1억개 만큼의 계산 가능
 * 메모리 : 256mb
 * 첫째 줄 : t(테스트케이스 개수)
 * 둘째 줄 : n(1이상 100_000 이하)
 * 셋째 줄, 넷째 줄 : 2*n개의 스티커 점수
 * ---
 * 출력 : 각 테스트케이스의 최대 점수
 */
public class BaekJoon9465 {

    static int[][] score;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // test case input
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<testCase; i++){
            // N input
            int N = Integer.parseInt(br.readLine());
            score = new int[2][N+1];
            dp = new int[2][N+1];

            // 점수 input
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                score[0][j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                score[1][j] = Integer.parseInt(st.nextToken());
            }

            // 초기값 세팅
            dp[0][1] = score[0][1];
            dp[1][1] = score[1][1];


            for(int j = 2; j<=N; j++){
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + score[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + score[1][j];
            }

            int result = Math.max(dp[0][N], dp[1][N]);
            sb.append(result).append('\n');

        }

        System.out.println(sb);
    }


}
