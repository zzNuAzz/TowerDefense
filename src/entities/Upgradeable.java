package entities;

public interface Upgradeable {
    boolean canUpgrade();
    void upgrade();
    int getUpCost();
}
