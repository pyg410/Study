package toss;

public class Sol2 {
    public static void main(String[] args) {

    }
    private static int solution(String s){
        if(s.length() < 3){
            return -1;
        }
        String[] arr = s.split("");

        int right = arr.length-1;
        int left = right-2;

        int answer = -1;

        while(left>=0){

            if( arr[left].equals(arr[right]) && arr[left].equals(arr[left+1])){
                answer = Math.max(answer, Integer.parseInt(arr[left] + arr[left+1] + arr[right]));
                // 배열이 정렬되서 주어지지 않음
            }


            left--;
            right--;
        }

        return answer;

    }
}
