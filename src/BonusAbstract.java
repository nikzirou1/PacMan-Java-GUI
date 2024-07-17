import javax.swing.*;
import java.awt.*;

public abstract class BonusAbstract implements Bonus {

    int x;
    int y;

    int actionPeriod = 0;



    Image image;



    @Override
    public void doAction(GameBoard board) {
    Thread thread = new Thread(new BonusRunable(this));
    thread.start();
    }



    public  BonusAbstract(int x, int y){
        this.x=x;
        this.y=y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }



    public abstract void upgrade();




    public abstract void noUpgrade();

}



