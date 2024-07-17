import javax.swing.*;
import java.awt.*;

public abstract class AbstractMovable implements Movable{
    int x, y;

    int deltaX;
    int deltaY;

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    Direction direction = Direction.IDLE;
    Direction nextDirection = Direction.IDLE;
     protected GameBoard board;

    public AbstractMovable(GameBoard board, int x, int y){
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public synchronized int getX() {
        return x;
    }

    @Override
    public synchronized int getY() {
        return y;
    }

    @Override
    public  Direction getDirection() {
        return direction;
    }

    public  void setDirection(Direction direction) {
        this.nextDirection = direction;
    }



    @Override
    public synchronized void  move() {

        int nextX=x;
        int nextY=y;
        int nextDeltaX=0;
        int nextDeltaY=0;
        direction=nextDirection;
        if(direction.equals(Direction.UP))
        {
            nextY = y-1;
            nextDeltaY= board.cellHigh;
        } else if(direction.equals(Direction.DOWN))
        {
            nextY = y+1;
            nextDeltaY= -board.cellHigh;
        } else if(direction.equals(Direction.RIGHT))
        {
            nextX = x+1;
            nextDeltaX= -board.cellWight;
        } else if (direction.equals(Direction.LEFT))
        {
            nextX= x-1;
            nextDeltaX= board.cellWight;
        }else return;
        if(board.canMove(nextX,nextY)){
            x = nextX;
            y = nextY;
            setDeltaX(nextDeltaX);
            setDeltaY(nextDeltaY);

        } else {
            nextDirection = direction = Direction.IDLE;
        }
    }



    @Override
    public synchronized void deltaMove() {

        if(direction.equals(Direction.UP))
        {
             setDeltaY(getDeltaY()-5);
        } else if(direction.equals(Direction.DOWN))
        {
            setDeltaY(getDeltaY()+5);
        } else if(direction.equals(Direction.RIGHT))
        {
            setDeltaX(getDeltaX()+5);
        } else if (direction.equals(Direction.LEFT))
        {
            setDeltaX(getDeltaX()-5);
      }
    }
}
