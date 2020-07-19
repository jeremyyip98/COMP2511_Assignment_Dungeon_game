package unsw.dungeon;

public class Boulder extends Entity implements Moveable{

    private Dungeon dungeon;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    

    @Override
    public boolean isWalkAllowed(Moveable m) {
        int newX = getX() - m.getX();
        int newY = getY() - m.getY();
        
        return dungeon.checkIsWalkAllowed(this, getX()+newX, getY()+newY) && m.ableBoulder();
    }

    @Override
    public void setPosition(int x, int y) {
        this.x().set(x);
        this.y().set(y);
    }

    @Override
    public boolean ableBoulder() {
        // boudlers can't push other boulders
        return false;
    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub

    }
}