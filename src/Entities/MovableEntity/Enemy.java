package Entities.MovableEntity;

public abstract class Enemy extends MovableEntity  {
    protected double healthPoint;
    protected double speed;
    protected double ammor;
    protected double rewardCoin;

    @Override
    public void update(long t) {
        super.update(t);
    }
}
