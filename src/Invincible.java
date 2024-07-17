import javax.swing.*;
import java.awt.*;

public class Invincible extends AbstractUpgrade {



        public Invincible(int x, int y, GameBoard board) {
            super(x, y, board);
            actionPeriod=10;
            image=new ImageIcon(".\\tiles\\x2Speed.png").getImage();
        }

        @Override
        public void upgrade() {
            board.setPlayerInvincible(true);
        }

        @Override
        public  void noUpgrade()
        {
            board.setPlayerInvincible(false);
        }



        @Override
        public int getScore() {
            return 0;
        }

        public Image getImage() {
            return image;
        }
    }


