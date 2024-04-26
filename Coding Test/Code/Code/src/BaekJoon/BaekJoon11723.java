package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BaekJoon11723 {

    private static StringBuilder sb;
    private static Set<Integer> set;
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        set = new HashSet<>();

        // main logic
        for(int i =0; i<testcase; i++){
            // command input
            String[] input = br.readLine().split(" ");
            String command = input[0];
            int num = -1;
            if(input.length>1) num = Integer.parseInt(input[1]);

            // logic
            operation(command, num);

        }

        // output
        System.out.println(sb);

    }

    private static void operation(String command, int num){
        switch (command){
            case "add":
                set.add(num);
                break;
            case "remove":
                set.remove(num);
                break;
            case "check":
                if(set.contains(num)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
                break;
            case "toggle":
                if(set.contains(num)) set.remove(num);
                else set.add(num);
                break;
            case "all":
                set = new HashSet<>();
                for(int i=1; i<=20; i++){
                    set.add(i);
                }
                break;
            case "empty":
                set = new HashSet<>();
                break;

        }

    }
}
