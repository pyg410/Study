package ThreeSixNineGame;

/**
 *  //이름과 오답율을 가지고 있는 클래스 만들기
 */
public class Player {

    private final String name;
    private final float errorRate; // 오답률 0.0 ~ 1.0 -> 0.6 오류가 날 확률이 0.6
    // 0이면 -> randNum


    public Player(String name, float errorRate){
        this.name=name;
        this.errorRate=errorRate;
    }

    public String getName(){
        return this.name;
    }

    /*
     get error status
     Math.ramdom() -> 특정 수를 구한다.

     */
    public boolean getErrorStatus(){
        // 랜덤한 수를 구한다.
        double randNum = Math.random(); // 0.0 <= n < 1.0

        // 랜덤한 수가 0~ this.errorRate 사이인지 구한다.

        // 사이에 있다면? -> true 반환
        if(randNum < errorRate){
            return true;
        }
        // 아니라면 -> false 반환
        else {
            return false;
        }
    }

}
