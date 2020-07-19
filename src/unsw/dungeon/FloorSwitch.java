package unsw.dungeon;

public class FloorSwitch extends Entity{

    /**
     * activated is true when boulder present
     */
    public boolean activated;
    private Dungeon dungeon;
    private Boulder partner;

    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        activated = false;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true;
    }

    public void update(Boulder b) {
        // check if there is a new boulder ontop
        if (b.getX() == this.getX() && b.getY() == this.getY() && !b.equals(partner) && this.activated == false){
            this.partner = b;
            this.activated = true;
            dungeon.triggerSwitch();
        } else if (b.equals(partner)){
            this.activated = false;
            partner = null;
            dungeon.untriggerSwitch();
        }
        System.out.println(dungeon.switchComplete());
    }



}