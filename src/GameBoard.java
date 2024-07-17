import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameBoard  extends JPanel {

    int playerDelay = normalDelay;
    public static final int speedUpDelay = 10;
    public static final int normalDelay = 100;
    int cellWight;
    int cellHigh;
    Thread moveThread;
    PlayerAnimationRunable playerRunnable;
    GhostAnimationRunable ghostRunable;
    Thread moveThreadEnemy;
    int lifeCount;
    int[][] initMap;
    MapItem[][] map;


    ArrayList<AbstractGhost> enemies = new ArrayList<>();
    ArrayList<Bonus> bonuses = new ArrayList<>();
    Player player;
    boolean PlayerInvincible = false;
    RedGhost redGhost;
    int boardWight;
    int boardHigh;
    int playerScore;
    boolean doubleCoins = false;

    public boolean isDoubleCoins() {
        return doubleCoins;
    }

    public void setDoubleCoins(boolean doubleCoins) {
        this.doubleCoins = doubleCoins;
    }

    public int getPlayerDelay() {
        return playerDelay;
    }
    public void setPlayerDelay(int playerDelay) {
        this.playerDelay = playerDelay;
    }

    public int getPlayerScore() {
        return playerScore;
    }
    public MapItem[][] getMap() {
        return map;
    }

    public void setMap(MapItem[][] map) {
        this.map = map;
    }


    public void addPlayerScore(int delta) {
        if(doubleCoins)
        {
            delta*=2;
        }
        this.playerScore += delta;
        GameWin.setScore(playerScore);

    }
    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
        GameWin.setLefeCnt(lifeCount);
    }



    public GameBoard(int[][] map) {
        super();
        initMap=map;
        boardHigh=map.length;
        boardWight=map[0].length;
        cellWight=50;
        cellHigh=50;
        setLifeCount(3);
        setFocusable(true);
        this.map = new MapItem[boardHigh][boardWight];
        levelCreation(map,true);



        addKeyListener(new KeyAdapter() {
            @Override
            public  void  keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    player.setDirection(Direction.UP);
                }else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    player.setDirection(Direction.DOWN);
                }else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    player.setDirection(Direction.LEFT);
                }else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    player.setDirection(Direction.RIGHT);
                }

            }
        });

        playerRunnable= new PlayerAnimationRunable(this);
        moveThread = new Thread(playerRunnable);

        ghostRunable=new GhostAnimationRunable(this);
        moveThreadEnemy = new Thread(ghostRunable);

    }

    public void levelCreation(int[][] map, boolean firstLife)
    {
        for(int i=0; i<boardHigh; i++){
            for (int j=0; j<boardWight;j++){
                MapItem item;
                if(firstLife) {
                    if (map[i][j] == 1){
                        item = new MapItem(TypesMapItems.WALL);
                    }
                    else{
                        item=new MapItem(TypesMapItems.FLOOR);
                    }
                    this.map[i][j]=item;
                }


                if(map[i][j]==0 && firstLife){
                    bonuses.add(new Coin(j,i));
                } else if (map[i][j]==3 && firstLife) // Bonus SpeedUp
                {
                    bonuses.add(new SpeedUp(j,i,this));
                }
                else if (map[i][j]==4 && firstLife)  // Invincible Player Bonus
                {
                    bonuses.add(new Invincible(j,i,this));
                }
                else if (map[i][j]==5 && firstLife) //Bonus CoinsX2
                {
                    bonuses.add(new DoubleCoins(j,i,this));
                }
                else if (map[i][j]==6 && firstLife) //Bonus BIG COIN
                {
                    bonuses.add(new Coin(j,i,100));
                }
                else if (map[i][j]==7 && firstLife) //Bonus BIG COIN
                {
                    bonuses.add(new Coin(j,i,300));
                }
                else if (map[i][j]==11 ) //Bonus BIG COIN
                {
                    enemies.add(new RedGhost(this,j,i));
//                    enemies.add(new RedGhost(this,j,i));
//                    enemies.add(new RedGhost(this,j,i));
//                    enemies.add(new RedGhost(this,j,i));
//                    enemies.add(new RedGhost(this,j,i));
                }
                else if (map[i][j]==21 ) //Bonus BIG COIN
                {
                    player = new Player(this,j,i);
                }



            }
        }
    }

    public void startLife()
    {
        enemies.clear();
        levelCreation(initMap,false);
    }

    public void gameOver()
    {
        playerRunnable.setRunning(false);
        ghostRunable.setRunning(false);
        Graphics gr=this.getGraphics();
        gr.setColor(Color.RED);
        gr.setFont(new Font("Arial", Font.BOLD, 30));
        gr.drawString("GAME OVER", 50,50);
    }

    public void startGame()
    {
        startLife();
        moveThread.start();
        moveThreadEnemy.start();
    }

    public void stopGame()
    {
//        moveThread.setRunning(false);
//        moveThreadEnemy.start();
    }

    public int getBoardWight() {
        return boardWight;
    }

    public void setBoardWight(int boardWight) {
        this.boardWight = boardWight;
    }

    public int getBoardHigh() {
        return boardHigh;
    }

    public void setBoardHigh(int boardHigh) {
        this.boardHigh = boardHigh;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Bonus> getBonuses() {
        return bonuses;
    }

    public ArrayList<AbstractGhost> getEnemies() {
        return enemies;
    }

    public boolean canMove(int x, int y) {
        if (x < 0 || y < 0 || x >= boardWight || y >= boardHigh) {
            return false;
        }
        return map[y][x].isWalkable();
    }

    public AbstractGhost isPlayerTouchEnemy(int x, int y) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getX() == x && enemies.get(i).getY() == y) {
                return enemies.get(i);
            }
        }
        return null;
    }

    public boolean isPlayerInvincible() {
        return PlayerInvincible;
    }

    public void setPlayerInvincible(boolean playerInvincible) {
        PlayerInvincible = playerInvincible;
    }

    public void playerTouchEnemy(AbstractGhost touchedGhost)
    {
        if(PlayerInvincible){
            enemies.remove(touchedGhost);
        }else{
            setLifeCount(getLifeCount()-1);
            if(lifeCount == 0)
            {
                gameOver();
            } else {
                startLife();
            }
        }
    }

    public Bonus isBonusWasTouched(int x, int y) {
        for (int i = 0; i < bonuses.size(); i++) {
            if (bonuses.get(i).getX() == x && bonuses.get(i).getY() == y) {
                return bonuses.get(i);
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {

            super.paint(g);
        for(int i = 0; i <boardHigh; i++)
        {
            for( int j = 0; j <boardWight; j++)
            {
                Image image =map[i][j].getImage();
                g.drawImage(image,j*cellWight,i*cellHigh,  null);

            }
        }

        for(int i=0; i<bonuses.size();i++){
            Image image =bonuses.get(i).getImage();
            g.drawImage(image,bonuses.get(i).getX()*cellWight,bonuses.get(i).getY()*cellHigh, cellWight, cellHigh, null);
        }

        Image playerImage =
                player.getImageRed();
        g.drawImage(playerImage,player.getX()*cellWight+player.getDeltaX(),player.getY()*cellHigh+player.getDeltaY(), cellWight, cellHigh,null);


        for(int j=0;j<enemies.size();j++) {
            AbstractGhost enemie = enemies.get(j);

            Image redGhostImage = enemie.getImageRed();
            g.drawImage(redGhostImage, enemie.getX() * cellWight+enemie.getDeltaX(), enemie.getY() * cellHigh+enemie.getDeltaY(), cellWight, cellHigh, null);



        }

    }

}







