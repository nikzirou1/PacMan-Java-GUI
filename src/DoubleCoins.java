import javax.swing.*;
import java.awt.*;

public class DoubleCoins extends AbstractUpgrade {

    public DoubleCoins(int x, int y, GameBoard board) {
        super(x, y, board);
        actionPeriod=10;
        image=new ImageIcon(".\\tiles\\x2Speed.png").getImage();
    }

    @Override
    public void upgrade() {
        board.setDoubleCoins(true);
    }

    @Override
    public  void noUpgrade()
    {
        board.setDoubleCoins(false);
    }



    @Override
    public int getScore() {
        return 0;
    }

    public Image getImage() {
        return image;
    }
}

