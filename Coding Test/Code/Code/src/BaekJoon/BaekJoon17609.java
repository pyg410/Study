package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;

public class BaekJoon17609 {

    static int result;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<t; i++){
            String str = br.readLine();
            LinkedList<String> list = new LinkedList<>();
            for(int j =0; j<str.length(); j++){
                list.add(String.valueOf(str.charAt(j)));
            }
            result = Integer.MIN_VALUE;
            abba(list, 1);

            if(result==1){
                sb.append(0).append("\n");
            } else if (result == 0) {
                sb.append(1).append("\n");
            } else{
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb);

    }

    static void abba(LinkedList<String> list, int cnt){
        System.out.println(list);

        if(list.size()==0){
            result = Math.max(result, cnt);
            return;
        }
        if(Objects.equals(list.get(0), list.get(list.size() - 1))){

            if(list.size() > 1){
                list.remove(0);
                list.remove(list.size()-1);
            }else{
                list.remove(0);
            }

            LinkedList<String> palindrome = list;
            abba(palindrome, cnt);
        }else{
            LinkedList<String> pseudo = new LinkedList<>(list);
            LinkedList<String> etc = new LinkedList<>(list);

            pseudo.remove(0);
            etc.remove(list.size()-1);

            abba(pseudo, cnt-1);
            abba(etc, cnt-1);

        }

    }
}
