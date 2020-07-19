package unsw.dungeon;

public interface EnemyStrategy {
    /**
     * This is a strategy pattern for changing the enemy movement at run time
     * https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
     * 
     * @param p the target player
     * @param e the enemy we are trying to move
     */
    public void move(Player p, Enemy e);

}