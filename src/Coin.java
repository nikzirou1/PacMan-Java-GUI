import javax.swing.*;
import java.awt.*;

public class Coin  extends BonusAbstract {
    protected int score=1;

    static final Image bigCoin100Image = new ImageIcon(".\\tiles\\x2Speed.png").getImage();
    static final Image bigCoin300Image = new ImageIcon(".\\tiles\\x2Speed.png").getImage();
    Image image;
    public Coin(int x, int y) {
        super(x, y);
        image=new ImageIcon(".\\tiles\\coin.png").getImage();
    }


    public Coin(int x, int y, int points)
    {
        super(x,y);
        if(points==100) {
            this.image = bigCoin100Image;
        } else if(points==300)
        {
            this.image = bigCoin300Image;
        }
        score = points;
    }

    @Override
    public int getScore() {
        return score;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void noUpgrade() {

    }
}
