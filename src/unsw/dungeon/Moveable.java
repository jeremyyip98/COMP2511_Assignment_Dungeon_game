package unsw.dungeon;

/**
 * Every entity that can move
 */
public interface Moveable {
    /**
     * true if can push boulder false if can't push boulder
     * @return
     */
    public boolean ableBoulder();

	public int getX();
    public int getY(); 
    /**
     * set the position
     * @param x
     * @param y
     */
    public void setPosition(int x, int y);
}