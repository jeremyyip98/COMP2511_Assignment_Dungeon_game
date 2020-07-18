package unsw.dungeon;

public class FloorSwitch extends Entity {

    public FloorSwitch(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        // TODO Auto-generated method stub
        return true;
    }

}