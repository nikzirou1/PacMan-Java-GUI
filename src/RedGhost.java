import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class RedGhost extends AbstractGhost {

    public enum MovingMode
    {
        Chaotic,
        Chase
    };

    Random rand = new Random();
    boolean playerisVisible=false;

    MovingMode mode = MovingMode.Chaotic;
    Image imageRed = new ImageIcon(".\\tiles\\ghost_red.png").getImage();
    Image imageYellow = new ImageIcon(".\\tiles\\ghost_yellow.png").getImage();
    public RedGhost(GameBoard board, int x,int y) {
        super(board,x,y);
    }

    @Override
    public void moveAlgorithm()
    {   playerisVisible=playerVisible();
        if(playerisVisible)
        {
            chaseAlgorithm();
        }
        else
        {
            moveChaoticAlgorithm();
        }
    }

    private void chaseAlgorithm()
    {
        Player player=board.getPlayer();
        Direction nextDirection = getDirection();
        double dx = player.getX()-x;
        double dy = player.getY()-y;
        int nextX= getX();
        int nextY= getY();
        if(Math.abs(dx) > Math.abs(dy))
        {
            if(dx>0){
                nextX+=1;
                nextDirection=Direction.RIGHT;
            }else if(dx<0) {
                nextX += -1;
                nextDirection = Direction.LEFT;
            }
        }
        else
        {
            if(dy>0){
                nextX+=1;
                nextDirection=Direction.DOWN;
            }else if(dy<0) {
                nextX += -1;
                nextDirection = Direction.UP;
            }
        }
        if(board.canMove(nextX,nextY))
        {
            setDirection(nextDirection);
        }else moveChaoticAlgorithm();

    }

    private synchronized void moveChaoticAlgorithm() {
        int counter = 0;
        int nextX = getX();
        int nextY = getY();
        Direction nextDirection = Direction.IDLE;
        int n = rand.nextInt(100);
        if (n > 30) {
            return;
        }
        do {
            int m = rand.nextInt(4);
            if (m == 0) {
                nextDirection = Direction.UP;
                nextY--;
            } else if (m == 1) {
                nextDirection = Direction.DOWN;
                nextY++;
            } else if (m == 2) {
                nextDirection = Direction.LEFT;
                nextX--;
            } else if (m == 3) {
                nextDirection = Direction.RIGHT;
                nextX++;
            }
            counter++;
        } while (!board.canMove(nextX,nextY) || counter < 5);
        setDirection(nextDirection);
    }

    @Override
    public Image getImageRed() {
        if(playerisVisible)
        {
            return imageRed;
        }
        else
        {
            return imageYellow;
        }
    }
}
