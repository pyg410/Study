package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BaekJoon1157 {
    static int max = Integer.MIN_VALUE;
    static int cnt = 1;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        // main logic

        n = n.toUpperCase();

        String[] str = n.split("");
        Map<String, Integer> map = new HashMap<>();
        for(int i =0; i<str.length; i++){
            if(map.containsKey(str[i])){
                map.compute(str[i], (key, oldValue) -> oldValue+1);
            }else{
                map.put(str[i], 1);
            }
        }
        map.values().forEach((i) -> {

            if(max == i) cnt++;
            else{
                if(max < i) {
                    cnt = 1;

                }
                max = Math.max(max, i);
            }

        });

        // output
        if(cnt > 1){
            System.out.println("?");
        }else{
            for (String key : map.keySet()) {
                if (map.get(key) == max) {
                    System.out.println(key);
                }
            }
        }


    }
}
