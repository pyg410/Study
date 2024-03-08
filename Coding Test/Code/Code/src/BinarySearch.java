import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {
    public static <T extends Comparable<T>> int binarySearch(T[] array, T target){
        int left = 0;
        int right = array.length;

        while(left<=right){
            int mid = (left + right) / 2;
            int cmp = target.compareTo(array[mid]);

            if(cmp == 0){
                return mid;
            }else if(cmp < 0){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("찾을 숫자를 입력해주세요 : ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] sortedArray = {"가", "나", "다", "라"};

        String target = st.nextToken();

        int resultIndex = binarySearch(sortedArray, target);

        if(resultIndex != -1){
            System.out.println("타겟 숫자 " + target + "은 인덱스 " + resultIndex + "에 있습니다.");
        }else {
            System.out.println("타겟 숫자 " + target + "은 배열에 존재하지 않습니다.");
        }
    }
}