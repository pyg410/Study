package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BaekJoon9935 {
    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        String[] boom = br.readLine().split("");

        Stack<String> stack = new Stack<>();

        for(int i =0; i<input.length; i++){

            int count = 0;
            stack.push(input[i]);
            if(stack.size()>= boom.length){


                for(int j =0; j<boom.length; j++){
                    if(stack.get(stack.size()-boom.length + j).equals(boom[j])){
                        count++;
                    }
                }
                if(count == boom.length){
                    for(int j=0; j<boom.length; j++){
                        stack.pop();
                    }
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else {
            for(String s : stack){
                sb.append(s);
            }
            System.out.println(sb);
        }


    }
}
