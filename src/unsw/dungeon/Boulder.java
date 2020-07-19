package unsw.dungeon;

public class Boulder extends Entity implements Moveable, PlayerObserver{

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
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() - 1)) {
            setPosition(getX(), getY() - 1);
        }
    }

    @Override
    public void moveDown() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() + 1)) {
            setPosition(getX(), getY() + 1);
        }
    }

    @Override
    public void moveLeft() {
        if (dungeon.checkIsWalkAllowed(this, getX() - 1, getY())) {
            setPosition(getX() - 1, getY());
        }
    }

    @Override
    public void moveRight() {
        if (dungeon.checkIsWalkAllowed(this, getX() + 1, getY())) {
            setPosition(getX() + 1, getY());
        }
    }

    @Override
    public void update(Player p) {
        if (p.getX() == this.getX() && p.getY() == this.getY()){
            // player just moved onto boulder
            if (p.getOldX() != p.getX() && p.getOldY() == p.getY()){
                // different x same y, move boulder left/right depending
                if (p.getOldX() > p.getX()){
                    this.moveLeft();
                } else {
                    this.moveRight();
                }
            } else 
            if (p.getOldX() == p.getX() && p.getOldY() != p.getY()){
                //same x different y, move boudler up/down
                if (p.getOldY() > p.getY()){
                    this.moveUp();
                } else {
                    this.moveDown();
                }
            }

        
        }

        // should also notify all switches TODO
    }
}