package ThreeSixNineGame;

public class ClapCounter {

    private static int clapCounter = 0;

    public void printClapCount(){
        System.out.println("모든 game의 박수 횟수 : " + clapCounter);
    }

    public static void clapCount(String result){
        String s = result;
        while(s.contains("clap")){
            s = s.replaceFirst("clap", "");
            clapCounter++;
        }
    }

}
