package unsw.dungeon;

public class Exit extends Entity implements PlayerObserver{

    private Dungeon dungeon;
    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true;
    }

	@Override
	public void update(Player p) {
        if (this.getX() == p.getX() && this.getY() == p.getY()){
            dungeon.setExitComplete();
        }
	}
}
