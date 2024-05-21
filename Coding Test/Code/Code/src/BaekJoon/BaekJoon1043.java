package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaekJoon1043 {
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int peopleNum = input[0];
        int partyNum = input[1];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int knowPeopleNum = input[0];
        Set<Integer> knowPeople = new HashSet<>();
        for(int i=1; i<=knowPeopleNum; i++){
            knowPeople.add(input[i]);
        }


        // main logic
        int cnt = 0;
        Set<Integer>[] set = new Set[partyNum];
        for(int i=0; i<partyNum; i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int thisPartyNum = input[0];
            int[] thisPartyPeople = Arrays.copyOfRange(input, 1, input.length);
            set[i] = new HashSet<>();
            for(int num : thisPartyPeople){
                set[i].add(num);
            }

            boolean flag = false;
            for(int people : thisPartyPeople){
                if(knowPeople.contains(people)){
                    flag = true;
                }
            }
            if(flag){
                for(int people : thisPartyPeople){
                    knowPeople.add(people);
                }
            }
        }

        for(Set<Integer> s : set){
            boolean flag =false;
            for(Integer num : s){
                if(knowPeople.contains(num)){
                    flag=true;
                }
            }

            if(!flag){
                cnt++;
            }
        }

        // output
        System.out.println(cnt);
    }
}
