package toss;

import java.util.Arrays;

public class Sol1 {
    public static void main(String[] args) {

    }

    public static int solution(int[] levels){
        int levelsLength = levels.length;
        Arrays.sort(levels);
        int k = levelsLength/4;
        if(levelsLength < 4){
            return -1;
        }

        int answerIndex = levelsLength - (k-1);
        return levels[answerIndex];
    }
}
