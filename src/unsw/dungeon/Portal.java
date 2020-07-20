package unsw.dungeon;

public class Portal extends Entity implements PlayerObserver{

    private Portal partner;
    private int id;

    public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true;
    }

    @Override
    public void update(Player p) {
        if (!(p.getOldX() == partner.getX() && p.getOldY() == partner.getY())){
            // has not come from a teleport
            p.setPosition(partner.getX(), partner.getY());
        }
    }

    public Portal getPartner() {
        return this.partner;
    }

    public void setPartner(Portal partner) {
        this.partner = partner;
    }

    public int getId() {
        return this.id;
    }

}