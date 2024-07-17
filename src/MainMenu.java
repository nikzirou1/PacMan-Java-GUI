import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        super("MainMenu");
        setSize(520,520);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton btnNewGame = new JButton("New Game");
        add(btnNewGame);
        btnNewGame.addActionListener(ev->{

            int[][] map={
                    {1,1,1,1,11,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,0,1,0,1,1,0,1,1},
                    {1,0,0,1,5,0,1,0,0,1},
                    {1,0,1,1,4,3,1,1,0,1},
                    {1,0,0,0,0,6,7,0,0,1},
                    {1,0,0,0,21,0,0,0,0,1},
                    {1,0,1,1,1,0,1,1,0,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1,1,1,1,1,1,1}
            };
           GameWin win=new GameWin(map);

        });



        JButton scoreButton = new JButton("Score");
        add(scoreButton);
        scoreButton.addActionListener(ev-> {
            new ScoreWin();
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }


}
