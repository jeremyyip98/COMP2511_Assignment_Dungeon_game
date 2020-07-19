package unsw.dungeon;

public class Portal extends Entity implements PlayerObserver{

    private String id;

    private Dungeon dungeon;

    public Portal(Dungeon dungeon, String id, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void update(Player p) {
        // TODO Auto-generated method stub
        Portal portal = dungeon.searchPortal(this);
        int exitX = portal.getX();
        int exitY = portal.getY();

        if (p.getX() == this.getX() && p.getY() == this.getY()){
            possibleDirection(p, exitX, exitY);
        }
    }

    public void possibleDirection(Player p, int exitX, int exitY) {
        boolean up = dungeon.checkIsWalkAllowed(p, exitX, exitY - 1);
        boolean down = dungeon.checkIsWalkAllowed(p, exitX, exitY + 1);
        boolean left = dungeon.checkIsWalkAllowed(p, exitX - 1, exitY);
        boolean right = dungeon.checkIsWalkAllowed(p, exitX + 1, exitY);
        String possibleDirection = "";

        if (up) {
            possibleDirection = "Up";
        } else if (down) {
            possibleDirection = "Down";
        } else if (left) {
            possibleDirection = "Left";
        } else if (right) {
            possibleDirection = "Right";
        }

        switch (possibleDirection) {
            case "Up":
                p.setPosition(exitX, exitY - 1);
                break;
            case "Down":
                p.setPosition(exitX, exitY + 1);
                break;
            case "Left":
                p.setPosition(exitX - 1, exitY);
                break;
            case "Right":
                p.setPosition(exitX + 1, exitY);
                break;
        }
    }

}