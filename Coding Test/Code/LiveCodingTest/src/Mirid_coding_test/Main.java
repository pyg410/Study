package Mirid_coding_test;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] companies = {"A fabdec 2", "B cebdfa 2", "C ecfadb 2"};
        String[] applicants = {"a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2"};
        String[] expects = {"A_bf", "B_ce", "C_d"};
        String[] result = solution.solution(companies, applicants);

        boolean assertion = true;
        if(result.length!= expects.length){
            assertion=false;
        }else{
            for (int i = 0; i<expects.length; i++){
                if (!Objects.equals(result[i], expects[i])) {
                    assertion = false;
                    break;
                }
            }
        }

        System.out.println("테스트 결과 : " + assertion);

    }
}
