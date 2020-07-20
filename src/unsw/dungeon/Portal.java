package unsw.dungeon;

public class Portal extends Entity implements PlayerObserver{

    private Portal partner;
    private int id;
    private boolean used; // true if player just used teleport

    public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.used = false;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true;
    }

    @Override
    public void update(Player p) {
        if (p.getX() == this.getX() && p.getY() == this.getY() && !this.used){
            // teleport not used
            this.used = true;
            partner.used = true;
            //p.setPosition(partner.getX(), partner.getY());
            p.x().set(partner.getX());
            p.y().set(partner.getY());
        } else 
        if (this.getX() != p.getX() || this.getX() != p.getY()){
            this.used = false;
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