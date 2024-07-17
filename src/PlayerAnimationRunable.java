class PlayerAnimationRunable implements  Runnable{
    GameBoard board;

    public void setRunning(boolean running) {
        this.running = running;
    }

    boolean running=true;
    public PlayerAnimationRunable(GameBoard board){
        this.board=board;
    }


    @Override
    public void run() {
        while(running){
            System.out.println("player timer 1");

            board.getPlayer().move();
            System.out.println("player timer 2");

            Thread animation = new Thread(new deltaPlayerAnimationRunable(board));
            animation.start();
            try {
                animation.join();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("player timer 3");

            Bonus touchedBonus = board.isBonusWasTouched(board.getPlayer().getX(), board.getPlayer().getY());
            System.out.println("player timer 3.1");
            if(touchedBonus != null)
            {
                board.addPlayerScore(touchedBonus.getScore());
                System.out.println("player timer 3.2");
                touchedBonus.doAction(board);
                System.out.println("player timer 3.3");
                board.getBonuses().remove(touchedBonus);
                System.out.println("player timer 3.4");
            }
            AbstractGhost touchedGhost = board.isPlayerTouchEnemy(board.getPlayer().getX(), board.getPlayer().getY());
            System.out.println("player timer 3.8");
            if(touchedGhost != null)
            {
                board.playerTouchEnemy(touchedGhost);
            }
            System.out.println("player timer 4");

            board.repaint();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("player end");
        }
        System.out.println("player timer stop");
    }
}

class deltaPlayerAnimationRunable implements  Runnable{
    GameBoard board;

    public void setRunning(boolean running) {
        this.running = running;
    }

    boolean running=true;
    public deltaPlayerAnimationRunable(GameBoard board){
        this.board=board;
    }


    @Override
    public void run() {
        for (int j = 0; j<10; j++){
            board.getPlayer().deltaMove();
            board.repaint();
            try {
                Thread.sleep(board.getPlayerDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}