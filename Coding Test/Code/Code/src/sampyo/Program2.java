package sampyo;

public class Program2 {
    public void solution(int size) {

        int[][] snail = new int[size][size];
        int num = 1; // 처음 들어갈 숫자.
        int count = size*size; //마지막 숫자.
        int i =0;
        int j =0;
        int width = size;
        int length = size-1;

        while(num <= count){
            for(int k=0; k<width; k++){
                snail[i][j] = num;
                j++;
                num++;
            }
            width--;
            i++;
            j--;

            for(int k=0; k<length; k++){
                snail[i][j] = num;
                i++;
                num++;
            }
            length--;
            i--;
            j--;

            for(int k=0; k<width; k++){
                snail[i][j] = num;
                j--;
                num++;
            }
            width--;
            i--;
            j++;

            for(int k=0; k<length; k++){
                snail[i][j] = num;
                i--;
                num++;
            }
            length--;
            i++;
            j++;
        }

        for(int a=0; a<snail.length; a++){
            for(int b =0; b<snail.length; b++){
                System.out.print(snail[a][b]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int size = 3;
        System.out.println("배열 크기" + size);

        Program2 pg = new Program2();
        pg.solution(size);
    }

}
