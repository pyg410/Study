package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BaekJoon21610 {

    private static int n;
    private static int m;
    private static int[][] a;

    private static int[][] ds;

    private static int totalWater = 0;

    private static boolean[][] cloud;
    private static int[] dx = {0, 0, -1, -1, -1, 0, 1,1,1};
    private static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        // input
        init();
        
        // solution
        solution();

    }

    private static void solution() {
        // 첫번째 구름 생성(주의할 점 index는 1부터 시작한다.)
        createFirstCloud();

        for(int i =0; i<m; i++){
            // 비가 온 배스킷을 저장하는 List생성
            List<RainyBasket> rainyBasketList = new ArrayList<>();

            // 구름 이동
            moveCloud(i);

            // 비 내림
            rain(rainyBasketList);

            // 구름 삭제
            resetCloud();

            // 물 복사 버그 실행
            copyWater(rainyBasketList);

            // 구름 업데이트
            updateCloud(rainyBasketList);


            if(i == m-1){
                // 바구니에 있는 물의 양 합 구하기
                totalWater = basketWaterSum();
            }
        }

        // 바구니에 있는 물의 양 합 출력
        System.out.println(totalWater);

    }

    private static int basketWaterSum() {
        int sum = 0;
        for(int i =1; i<=n; i++){
            for(int j =1; j<=n; j++){
                sum += a[i][j];
            }
        }
        return sum;
    }

    private static void updateCloud(List<RainyBasket> rainyBasketList) {
        cloud = new boolean[n+1][n+1];

        for(int i=1; i<=n; i++){
            for(int j =1; j<=n; j++){
                if(a[i][j] >=2){
                    cloud[i][j] = true;
                    a[i][j] -= 2;
                }
            }
        }

        for(RainyBasket rainyBasket : rainyBasketList){
            if(cloud[rainyBasket.x][rainyBasket.y]){
                a[rainyBasket.x][rainyBasket.y] += 2;
                cloud[rainyBasket.x][rainyBasket.y] = false;
            }
        }




    }

    private static void copyWater(List<RainyBasket> rainyBasketList) {
        int[] copyWaterDirectionX = {-1, 1, 1, -1}; // 왼쪽위, 오른쪽 위, 오른쪽아래, 왼쪽아래
        int[] copyWaterDirectionY = {-1, -1, 1, 1};

        for(RainyBasket rainyBasket : rainyBasketList){
            int basketCnt = 0;
            for(int i =0; i<4; i++){
                int nx = rainyBasket.x + copyWaterDirectionX[i];
                int ny = rainyBasket.y + copyWaterDirectionY[i];

                if(0<nx && nx <=n && 0<ny && ny <=n && a[nx][ny] > 0){
                    basketCnt++;
                }
            }

            a[rainyBasket.x][rainyBasket.y] += basketCnt;
        }
    }

    private static void resetCloud() {
        cloud = new boolean[n+1][n+1];
    }

    private static void rain(List<RainyBasket> rainyBasketList) {

        for(int i=1; i<=n; i++){
            for(int j =1; j<=n; j++){
                if(cloud[i][j]){
                    a[i][j]++;
                    // 물이 증가한 칸에 물복사 마법써야해서 이 정보가 중요함.
                    rainyBasketList.add(new RainyBasket(i, j));
                }
            }
        }
    }

    private static void createFirstCloud() {

        cloud[n][1] = true;
        cloud[n][2] = true;
        cloud[n-1][1] = true;
        cloud[n-1][2] = true;

    }

    private static void moveCloud(int moveIdx) {

        boolean[][] copyCloud = new boolean[n+1][n+1];

        for(int i =1; i<cloud.length; i++){
            for(int j =1; j< cloud.length; j++){
                if(cloud[i][j]){
                    int nx = i + XDistance(moveIdx);
                    int ny = j + YDistance(moveIdx);

                    if(nx > n){
                        nx = nx - n;
                    } else if (nx <= 0) {
                        nx = n + nx;
                    }

                    if(ny > n){
                        ny = ny - n;
                    } else if (ny <= 0) {
                        ny = n + ny;
                    }

                    copyCloud[nx][ny] = true;
                }
            }
        }

        cloud = copyCloud;
    }

    private static int YDistance(int moveIdx) {
        return (dy[ds[moveIdx][0]] * ds[moveIdx][1])%n;

    }

    private static int XDistance(int moveIdx) {
        return (dx[ds[moveIdx][0]] * ds[moveIdx][1])%n;
    }

    private static void init() throws
    IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        a = new int[n+1][n+1];
        ds = new int[m][2];// d:0, s:1
        cloud = new boolean[n+1][n+1];

        for(int i =1; i<=n; i++){
            input = br.readLine().split(" ");
            for(int j =1; j<=n; j++){
                a[i][j] = Integer.parseInt(input[j-1]);
            }
        }

        for(int i =0; i<m; i++){
            input = br.readLine().split(" ");
            ds[i][0] = Integer.parseInt(input[0]);
            ds[i][1] = Integer.parseInt(input[1]);
        }

    }

    private static class RainyBasket{
        int x;
        int y;

        public RainyBasket(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
