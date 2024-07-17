public class BonusRunable implements Runnable {

    BonusAbstract bonusAbstract;
    public BonusRunable(BonusAbstract bonusAbstract) {
        this.bonusAbstract = bonusAbstract;
    }

    @Override
    public void run() {
        bonusAbstract.upgrade();
        if(bonusAbstract.actionPeriod!=0) {
            try {
                Thread.sleep(bonusAbstract.actionPeriod * 1000);
                bonusAbstract.noUpgrade();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
