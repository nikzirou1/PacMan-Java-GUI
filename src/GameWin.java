import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {
    private  static JLabel scoreVal;

    private static JLabel lbLifesCnt;

    public GameWin(int[][] map)
    {
        super("PacMan");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520,520);
        setLayout(new BorderLayout());
        JPanel topPanel=new JPanel();
        topPanel.setSize(50,50);
        add(topPanel,BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());
        lbLifesCnt=new JLabel();
        topPanel.add(lbLifesCnt, BorderLayout.WEST);
        JPanel topRightPanel = new JPanel();
        topPanel.add(topRightPanel,BorderLayout.EAST);

        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel scoreTitle=new JLabel("Score");
        scoreVal=new JLabel("000");

        topRightPanel.add(scoreTitle);
        topRightPanel.add(scoreVal);



        GameBoard board = new GameBoard(map);
        board.setLayout(new BorderLayout());
        add(board);
        setVisible(true);
        board.startGame();
    }
    public static void setScore(int val){
        String strVal=String.format("%03d",val);
        scoreVal.setText(strVal);
    }
    public static void setLefeCnt(int val){

        lbLifesCnt.setText(""+val);
    }
}
