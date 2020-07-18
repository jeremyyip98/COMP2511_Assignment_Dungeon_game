package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        // TODO Auto-generated method stub
        return true;
    }

}