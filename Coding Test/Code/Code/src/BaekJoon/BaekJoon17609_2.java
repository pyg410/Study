package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon17609_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i =0; i<t; i++){
            StringBuilder sb = new StringBuilder(br.readLine());
            int result = 2;

            if(sb.toString().equals(sb.reverse().toString())){
                result = 0;
            }
            else{
                int left = 0;
                int right = sb.toString().length()-1;
                while (left < right){
                    // 첫번째 문자와 마지막 문자가 같지 않다면,
                    if(sb.charAt(left) != sb.charAt(right)){
                        StringBuilder leftStr = new StringBuilder(sb).deleteCharAt(left);
                        StringBuilder rightStr = new StringBuilder(sb).deleteCharAt(right);

                        // 한번이라도 문자를 삭제했을 때 회문이라면 유사회문이 됨. 그 외에는 전부 회문과 유사회문이 아님.
                        if(leftStr.toString().equals(leftStr.reverse().toString()) || rightStr.toString().equals(rightStr.reverse().toString())){
                            result = 1;
                        }
                        break;
                    }
                    left++;
                    right--;
                }
            }

            System.out.println(result);

        }
    }
}
