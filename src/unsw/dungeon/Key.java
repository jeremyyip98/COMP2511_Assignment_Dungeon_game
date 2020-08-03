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
        System.out.println("ableDDDD: " + able);

        if (p.getKey() != null){
            // place players key on floor
            p.placeKey();
            System.out.println("ALREADY HOLDING A KEY: ");
        }
        if (!able && (p.getOldX() != getX() || p.getOldY() != getY())){
            able = true;
        }
        // pickup key on floor
        p.setKey(this);
        System.out.println("ab: " + able);
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
