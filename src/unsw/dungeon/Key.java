package unsw.dungeon;

public class Key extends PickUp {
    private int id;
    private Door partner;

    public Key(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y);
        this.id = id;
    }

    @Override
    public void pickup(Player p) {
        // if already holding a key
        if (p.getKeyID() != -1){
            // place current key on floor
            int prevID = p.getKeyID();
            Key prevKey = new Key(dungeon, p.getX(), p.getY(), prevID);
            dungeon.addEntity(prevKey);
        }
        // pickup key on floor
        p.setKeyID(id);
    }
    

    public Door getPartner() {
        return this.partner;
    }

    public void setPartner(Door partner) {
        this.partner = partner;
    }

}