import javax.swing.*;
import java.awt.*;

public class Player extends AbstractMovable{

    private boolean isSpeedUp;

    Image[] images = {new ImageIcon(".\\tiles\\pac1.png").getImage(),
            new ImageIcon(".\\tiles\\pac2.png").getImage(),
            new ImageIcon(".\\tiles\\pac3.png").getImage(),
            new  ImageIcon(".\\tiles\\pac4.png").getImage()};

    int currentFrameNumber = 0;


    public Player(GameBoard board, int x, int y){
        super(board,x,y);
        direction = Direction.IDLE;
    }

public void speedUp(boolean isSpeedUp)
{
this.isSpeedUp = isSpeedUp;

}



    @Override
    public int getSpeed() {
        if(isSpeedUp)
        {
            return 10;
        } else
        {
            return  5;
        }
    }



    @Override
    public Image getImageRed() {
        Image image1 = images[currentFrameNumber];
        currentFrameNumber++;
        currentFrameNumber%=images.length;
        return  image1;
    }
}
