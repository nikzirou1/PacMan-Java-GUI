import javax.swing.*;
import java.awt.*;

public class SpeedUp extends AbstractUpgrade{
    public SpeedUp(int x, int y, GameBoard board) {
        super(x, y, board);
        actionPeriod=10;
        image=new ImageIcon(".\\tiles\\x2Speed.png").getImage();
    }

    @Override
    public void upgrade() {
    board.setPlayerDelay(GameBoard.speedUpDelay);
    }

    @Override
    public  void noUpgrade()
    {
        board.setPlayerDelay(GameBoard.normalDelay);
    }



    @Override
    public int getScore() {
        return 0;
    }

    public Image getImage() {
        return image;
    }
}
