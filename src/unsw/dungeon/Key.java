package unsw.dungeon;

public class Key extends PickUp {
    private int id;

    private Door partner;

    private Boolean able;

    public Key(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y);
        this.id = id;
    }

    @Override
    public void pickup(Player p) {
        // hide this key 
        this.x().set(0);
        this.y().set(0);
        this.able = false;
        // if already holding a key
        if (p.getKey() != null && able == true){
            // place players key on floor
            p.placeKey();
            System.out.println("ALREADY HOLDING A KEY: ");
        }
        if (!able && (p.getX() != getX() || p.getY() != getY())){
            able = true;
        }
        // pickup key on floor
        p.setKey(this);
    }

    public int getId() {
        return this.id;
    }

    public Door getPartner() {
        return this.partner;
    }

    public void setPartner(Door partner) {
        this.partner = partner;
    }

	public void setAble(boolean b) {
        this.able = b;
	}

}
