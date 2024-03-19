package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int size = N.length();
        long result = 0;
        // A의 대문자 아스키코드는 65 이걸 10로 만드려면? 55를 빼면 됨.
        for(int i = 0; i<size; i++){
            char last = N.charAt(size-1-i);
            long a;
            if('0'<= last && last<= '9'){
                a= last - '0';
            }else{
                a = N.charAt(size-1-i) - 55;
            }

            result+= a * Math.pow(B, i);

        }

        System.out.println(result);

    }
}
