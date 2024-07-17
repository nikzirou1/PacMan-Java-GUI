import javax.swing.*;
import java.awt.*;

public class MapItem {
    boolean isWalkable = true;
    int score;

    Image image;
    private static final Image emptyImage = new ImageIcon("").getImage();

    TypesMapItems type;

    public boolean isWalkable() {
        return isWalkable;
    }

    public Image getImage() {
//        if(isWalkable && score==0) {
//            return emptyImage;
//        } else {
            return image;
//        }
    }


    public MapItem(TypesMapItems type) {
        this.type = type;
        if (this.type == TypesMapItems.FLOOR) {
            image = new ImageIcon(".\\tiles\\floor.png").getImage();
            isWalkable = true;

        }else
        {
            image = new ImageIcon(".\\tiles\\wall.png").getImage();
            isWalkable = false;
        }
    }


}


