package ThreeSixNineGame;

public class ThreeSixNineGameInSeoul extends ThreeSixNineGame{
    public ThreeSixNineGameInSeoul() {
        super("서울");
    }

    @Override
    public String do369(int number) {
        boolean isThreeSixNine = false; // 3,6,9가 포함되었는지 판단하는 필드

        // 정수 타입을 문자열로 변환한다.
        String num = String.valueOf(number);

        // 해당 문자열 내에 3,6,9가 있는지 판단한다.
        if(num.contains("3")){

            isThreeSixNine = true;
        } else if(num.contains("6")){

            isThreeSixNine = true;
        } else if(num.contains("9")){

            isThreeSixNine = true;
        }

        // 3,6,9가 존재하면? -> 숫자 반환
        if(!isThreeSixNine){
            return num;
        }
        // 존재하지 않는 경우 -> "clap"을 반환한다.
        else{
            return "clap";
        }
    }
}
