public class ParametricSearch {
    // 파라메트릭 서치를 위한 조건 함수
    public static boolean condition(int[] array, int value, int threshold) {
        // 여기에서는 배열의 합이 threshold보다 큰지 여부를 확인합니다.
        int sum = 0;
        for (int num : array) {
            sum += Math.min(num, value);
        }
        return sum > threshold;
    }

    public static int parametricSearch(int[] array, int threshold){
        int left = 0;
        int right = array.length-1;
        int result = -1;

        while(left <= right){
            int mid = left + ((right - left) / 2); // Overflow 예방하기 위한 방법

            if(condition(array, mid, threshold)){
                result = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int threshold = 9;
        int result = parametricSearch(array, threshold);
        System.out.println("가장 작은 값: " + result);
    }

}
