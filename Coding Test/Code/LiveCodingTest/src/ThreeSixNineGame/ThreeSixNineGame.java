package ThreeSixNineGame;

public abstract class ThreeSixNineGame{
    private final String location;
    public ThreeSixNineGame(String location){
        this.location=location;
    }

    /**
     * 1 - 100
     * edge case
     * 1 미만의 정수
     * 100 초과의 정수

     number 에 3,6,9가 포함되면 "clap", 아니면 입력받은 숫자를 String으로 리턴
     do369(16) -> "clap"
     do369(12) -> "12"
     do369(33) -> "clap", 3이 두번나왔지만 clap은 한번

     숫자 -> 숫자 내에, 3,6,9가 들어있는 것을 판단한다.
     number -> String으로 변환. -> String.charAt() -> 3,6,9가 존재하는지 판단.

     3,6,9가 존재하면? -> 숫자 반환
     존재하지 않으면? -> "clap" 반환


     */
    public abstract String do369(int number);

    /**
     게임에 참가하는 플레이어의 이름 배열을 받아서 게임을 진행.
     결과를 sout 으로 화면에 출력해주세요.
     정확히 100회의 게임이 진행 되도록 해주세요.
     예제. "영수", "광수", "영철", "상철" 이 입력된경우
     영수: 1
     광수: 2
     영철: clap
     상철: 4
     ..중략..
     상철: 100

     parmams : String[]
     return Type : void

     1. 100회 게임이 진행되야한다.

     반복문 -> 100회 진행
     String 배열의 index 0~4까지 반복한다.

     do369로 양수 or clap의 유무를 판단. x

     추가 요구사항
     - 플레이어의 수는 변동될 수 있다.

     */
    /*
    문제 2 요구사항
    1. playerGame 메서드의 String[] -> Player[]
    2. player의 오답율은 매 player의 턴마다 오답을 이야기할 수 있어야 한다. ex,. clap 반환해야할 때 숫자를 반환, 잘못된 숫자를 반환
    3. 오답이 반환되는 경\우 게임은 종료된다.
    4. 게임은 오답이 나올 때까지 무한히 수행되어야 한다. done
     */
    public void playGame(Player[] players) {

        // 오답을 말하기 전까지 무한히 반복된다.
        boolean isGameOver = false;
        int idx = 1;


        while(!isGameOver){
            // players의 원소 수 만큼 반복해준다.
            for(Player player : players){

                // isGameOver의 상태를 변경한다.
                isGameOver = player.getErrorStatus();

                String do369Result = do369(idx);
                ClapCounter.clapCount(do369Result);

                // isGameOver가 true라면
                if(isGameOver){
                    System.out.println(location + " - " + player.getName() + ": " + wrongAnswer(do369Result));
                    break;
                } else {
                    // 출력
                    System.out.println(location + " - " + player.getName() + ": " + do369Result);
                }

                // idx +1
                idx++;

            }
        }

    }

    public String wrongAnswer(String answer){
        int randNum = (int)(Math.random()*100 + 1);
        // clap이 들어온다면,
        // 랜덤한 수를 문자열로 출력
        if(answer.contains("clap")){
            return String.valueOf(randNum);
        }

        // 숫자가 들어온다면
        // 숫자 + 랜덤 수를 더해서 문자열로 return
        else{
            while(Integer.parseInt(answer) == randNum){
                randNum = (int)(Math.random()*100 + 1);
            }
            return String.valueOf(randNum);
        }



    }

    public static void main(String[] args) {
        Player[] players = new Player[]{
                new Player("영수", 0.01f),
                new Player("광수", 0.0f),
                new Player("영철", 0.0f),
                new Player("상철", 0.0f)
        };
        ClapCounter cp = new ClapCounter();

        Thread t1 = new Thread(() -> new ThreeSixNineGameInSeoul().playGame(players));
        Thread t2 = new Thread(() -> new ThreeSixNineGameInBusan().playGame(players));
        t1.start();
        t2.start();

        // Thread가 종료되었는지 0.5초에 1회 확인.
        // Thread 종료시 총 박수 횟수 출력 후 종료
        try{
            while(true){
                Thread.sleep(500);
                if(t1.getState().equals(Thread.State.TERMINATED) && t2.getState().equals(Thread.State.TERMINATED)){
                    cp.printClapCount();
                    break;
                }

            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }


}