public class GhostAnimationRunable implements  Runnable {

    GameBoard board;

    public void setRunning(boolean running) {
        this.running = running;
    }

    boolean running=true;
    public GhostAnimationRunable(GameBoard board){
        this.board=board;
    }


    @Override
    public void run() {
        int bonusGenerationCounter = 0;
        while(running){
            System.out.println("ghost timer1");
            bonusGenerationCounter++;
            if(bonusGenerationCounter >= 5)
            {
                generateBonus();
                bonusGenerationCounter = 0;
            }
            System.out.println("ghost timer2");
            for(int i=0;i<board.getEnemies().size();i++ ){
                board.getEnemies().get(i).moveAlgorithm();
                board.getEnemies().get(i).move();
            }

            Thread animation = new Thread(new deltaGhostAnimationRunable(board));
            animation.start();
            try {
                animation.join();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("ghost timer3");



           board.repaint();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("ghost timer end");
        }
        System.out.println("ghost timer precess end");
    }

    private void generateBonus()
    {
        if (board.getEnemies().size()==0) return;
        if(Math.random()>0.25) return;
        int ghostNumber = (int)Math.floor (Math.random()*board.getEnemies().size());
        int bonusNumber = (int)Math.floor (Math.random()*5);
        BonusAbstract bonus = null;
        AbstractGhost ghost = board.getEnemies().get(ghostNumber);
        int x=ghost.getX();
        int y = ghost.getY();
        switch (bonusNumber)
        {
            case 0: bonus = new SpeedUp(x,y,board);  break;
            case 1: bonus = new Invincible(x,y,board);  break;
            case 2: bonus = new DoubleCoins(x,y,board);  break;
            case 3: bonus = new Coin(x,y,100);  break;
            case 4: bonus = new Coin(x,y,300);  break;
        }
        board.getBonuses().add(bonus);

    }
}

class deltaGhostAnimationRunable implements  Runnable{
    GameBoard board;

    public void setRunning(boolean running) {
        this.running = running;
    }

    boolean running=true;
    public deltaGhostAnimationRunable(GameBoard board){
        this.board=board;
    }


    @Override
    public void run() {
        for (int j = 0; j<10; j++){
            for(int i=0;i<board.getEnemies().size();i++ ){
                board.getEnemies().get(i).deltaMove();
            }
//            board.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
