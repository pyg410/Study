package ThreeSixNineGame;

public class ThreeSixNineGameInBusan extends ThreeSixNineGame{


    public ThreeSixNineGameInBusan() {
        super("부산");
    }

    @Override
    public String do369(int number) {
        boolean isThreeSixNine = false; // 3,6,9가 포함되었는지 판단하는 필드

        // 정수 타입을 문자열로 변환한다.
        String num = String.valueOf(number);
        StringBuilder sb = new StringBuilder();

        while(num.contains("3") || num.contains("6") || num.contains("9")){
            // 해당 문자열 내에 3,6,9가 있는지 판단한다.
            if(num.contains("3")){
                isThreeSixNine = true;
                num = num.replaceFirst("3", "");
                sb.append("clap");
            }
            if(num.contains("6")){
                num = num.replaceFirst("6", "");
                sb.append("clap");
                isThreeSixNine = true;
            }
            if(num.contains("9")){
                isThreeSixNine = true;
                num = num.replaceFirst("9", "");
                sb.append("clap");
            }
        }


        // 3,6,9가 존재하지 않으면? -> 숫자 반환
        if(!isThreeSixNine){
            return num;
        }
        // 존재하는 경우 -> "clap"을 반환한다.
        else{
            return sb.toString();
        }
    }

}
