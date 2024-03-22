package Programmers;

import java.util.*;
import java.io.*;
class Solution1_2 {
    /**

     명령어 기반으로 표의 행을 선택, 삭제, 복구하는 프로그램

     파란색으로 칠해진 칸 -> 현재 선택된 행

     한 번에 한 행만 선택 가능.

     표의 범위(0~ 마지막 행) 벗어날 수 없음.

     U X :현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.  up x

     D X : down x

     C : 현재 선택된 행 삭제, 아래 행 선택. 단, 삭제된 행이 가장 마지막 행이면 윗행 선택

     Z : 가장 최근에 삭제된 행을 원래대로 복구.(현재 선택된 행은 바뀌지 않는다.)

     LinkedList쓰자.
     Stack -> 삭제된 것들 저장
     bool = isDeleted[] -> 삭제되었는지 여부

     주의할 점 : index범위를 0~list.size()-1

     n -> 표의 행 개수
     k -> 처음 선택된 Indx
     cmd -> 명령어 집합.


     */
    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        Stack<Integer> deleted = new Stack<>();

        int idx = k;
        int tbSize = n;

        for(int i =0; i<cmd.length; i++){



            if(String.valueOf(cmd[i].charAt(0)).equals("U")){
                idx-= cmd[i].charAt(2) -'0';
            }
            else if(String.valueOf(cmd[i].charAt(0)).equals("D")){
                idx+= cmd[i].charAt(2) -'0';
            }
            else if(String.valueOf(cmd[i].charAt(0)).equals("C")){
                deleted.add(idx);
                tbSize--;
                if(tbSize==idx){
                    idx--;
                }
            }
            else if(String.valueOf(cmd[i].charAt(0)).equals("Z")){
                if(deleted.pop() <= idx){
                    idx++;
                }
                tbSize++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            sb.append("O");
        }

        while(!deleted.isEmpty()){
            int s = deleted.pop();
            sb.setCharAt(s, 'X');
        }

        answer = sb.toString();
        return answer;

    }
}