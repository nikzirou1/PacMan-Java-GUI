import java.awt.*;

public abstract class AbstractGhost extends AbstractMovable {
    public AbstractGhost(GameBoard board,int x, int y) {
        super(board, x, y);

    }


    protected boolean playerVisible()
    {
        Player player=board.getPlayer();
        double dx = player.getX()-x;
        double dy = player.getY()-y;
        double angle;
        angle = dy/dx;
        int delta;

        if (Double.isInfinite(angle)){
            int x1=x;
            delta=(int)Math.signum(dy);
            for(int y1=y; y1>=0 && y1<board.getBoardHigh();y1+=delta){
                if(x1 == player.getX() && player.getY() == y1)
                {
                    return true;
                }
                if(!board.getMap()[y1][x1].isWalkable())
                {
                    return false;
                }
            }
            return false;
        }else{
            delta=(int)Math.signum(dx);
            double b=y-x*angle;
            for(int i = x; i >=0 && i<board.getBoardWight(); i+=delta)
            {
                int x1 =i;
                int y1 =(int) Math.round(x1*angle+b);
                if(x1 == player.getX() && player.getY() == y1)
                {
                    return true;
                }

                if(!board.getMap()[y1][x1].isWalkable())
                {
                    return false;
                }
            }
            return false;
        }
    }
    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Image getImageRed() {
        return null;
    }


    public abstract void moveAlgorithm();



}
