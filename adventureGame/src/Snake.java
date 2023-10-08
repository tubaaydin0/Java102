import java.util.Random;

public class Snake extends Obstacle { // Yılan sınıfı
    static   Random rand=new Random();


    public Snake(){
        super(4,"Yılan",rand.nextInt(4)+3, 12,0);
    }
}
