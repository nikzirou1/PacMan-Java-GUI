public abstract class AbstractUpgrade extends BonusAbstract {

    protected GameBoard board;

    public AbstractUpgrade(int x, int y, GameBoard board) {
        super(x, y);
        this.board = board;

    }

    public abstract void upgrade();

    public abstract void noUpgrade();

}
