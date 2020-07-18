package unsw.dungeon;

public class Portal extends Entity {

    public Portal(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        // TODO Auto-generated method stub
        return true;
    }

}