package sampyo;

public class Program {
    public void solution(int[] a){
        // a 배열은 양수 및 음수
        // 요소 개수는 10개 이내
        // 배열 내 두 값을 곱했을 때 최대 값이 되어야 함.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<a.length-1; i++){

            for(int j = i+1; j<a.length; j++){
                max = Math.max(a[i]*a[j], max);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        int[] a = {4, -6, 3, 2, -7};
        Program pg = new Program();
        pg.solution(a);
    }

}

