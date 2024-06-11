//package BaekJoon;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Stack;
//
//public class BaekJoon26122 {
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//
//        String[] arr =br.readLine().split("");
//        Stack<String> stackN = new Stack<>();
//        Stack<String> stackS = new Stack<>();
//        boolean isN = true;
//        int maxN = 0;
//        int cntN = 0;
//        for(int i =0; i<n; i++){
//
//            if(arr[i].equals("N") && isN){
//                stackN.push(arr[i]);
//            }
//            else if(arr[i].equals("N") && !isN){
//                stackN = new Stack<>();
//                stackN.push(arr[i]);
//            }
//            else if(arr[i].equals("S") && isN){
//                isN = false;
//                stackN.pop();
//                if(stackN.isEmpty()){
//                    cntN
//
//                }
//            }
//        }
//    }
//}
