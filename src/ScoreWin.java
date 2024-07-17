import javax.swing.*;
import java.awt.*;

public class ScoreWin extends JFrame {

    public ScoreWin()
    {
        super("Score");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520,520);
        setLayout(new BorderLayout());
        JScrollBar bar = new JScrollBar();
        add(bar,BorderLayout.CENTER);
        JList<Score> list = new JList<Score>();
        bar.add(list);
        setVisible(true);

    }
}
