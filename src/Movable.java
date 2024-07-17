import java.awt.*;

public interface Movable {

    int getX();

    int getY();

    Direction getDirection();

    int getSpeed();

    public void move();

    public Image getImageRed();

    int getDeltaX();
    int getDeltaY();

    public void deltaMove();
}
