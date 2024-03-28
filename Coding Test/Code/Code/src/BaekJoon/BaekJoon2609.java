package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = eucGCD(a, b);
        int lcm = eucLCM(a, b, gcd);

        System.out.println(gcd);
        System.out.println(lcm);

        br.close();

    }

    private static int eucGCD(int a, int b){
        if(b == 0){
            return a;
        }
        return eucGCD(b, a%b);

    }

    private static int eucLCM(int a, int b, int gcd){
        return a*b / gcd;
    }
}
