package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * -- 문제
 * 1, 5, 10, 50, 100, 500원이 있다.
 * 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세는 프로그램 작성할 것
 *
 * -- 입력
 * 테스트 케이스 개수(T 1이상 10 이하)
 * 동전의 가지수 N(1이상 20이하)
 * N가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다.
 * 각 금액은 1원부터 10000원까지 있을 수 있고 공백으로 구분됨
 * N가지 동전으로 만들어야 할 금액 M(1 이상 10000 이하)이 주어진다.
 *
 * ex
 * 3 -> T
 * 2 -> N
 * 1 2 -> 각 금액
 * 1000 -> 만들 금액 M
 *
 * 1, 2로 3을 만드는 방법은?
 * 111 / 12
 * 4를 만드는 방법은?
 * 1111 / 22 / 112
 * 5를 만드는 방법은?
 * 11111 / 1112 / 122
 *
 * 5/가장 큰 금액?
 *
 * dp[0]은 항상 1임. 어떤 동전이 들어와도 만드는 방법은 하나도 쓰지 않는 방법 1개.
 * dp[m] = dp[m] + dp[m-특정 동전]
 *
 *
 */
public class BaekJoon9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // testcase
        for(int t = 0; t<testCase; t++){
            // input
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n];
            // coin[] input
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }
            // m input
            int m = Integer.parseInt(br.readLine());
            int[] dp = new int[m+1];

            dp[0] = 1;

            for(int i = 0; i<n; i++){
                for( int j = coin[i]; j<=m; j++){
                    dp[j] += dp[j-coin[i]];
                }
            }

            sb.append(dp[m]).append('\n');

        }
        System.out.println(sb);
    }
}
