import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Board mainBoard = new Board();
        mainBoard.defaultBoard();

        //랜덤으로 순서 정하기
        //0 : 우선들어온사람이 먼저
        //1 : 가위바위보로 정하기
        Random random = new Random();
        int orderRandom = random.nextInt(2);
        int first;
        if(orderRandom==1){
            //우선들어온사람
        }else{
            // 가위바위보로 정해진 사람
        }


    }
}