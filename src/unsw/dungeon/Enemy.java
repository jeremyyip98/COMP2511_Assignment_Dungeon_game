package unsw.dungeon;

public class Enemy extends Entity implements Moveable, PlayerObserver, EnemyStrategy{

    private Dungeon dungeon;


    private EnemyStrategy strategy;
    private EnemyScared scared = new EnemyScared();
    private EnemyAggressive aggressive = new EnemyAggressive();

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.setStrategy(aggressive); // initially aggressive
    }

    public EnemyStrategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(EnemyStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true; // need to walk on to die
    }

    @Override
    public boolean ableBoulder() {
        // can't push boulder
        return false;
    }

    @Override
    public boolean moveUp() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() - 1)) {
            setPosition(getX(), getY() - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean moveDown() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() + 1)) {
            setPosition(getX(), getY() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean moveLeft() {
        if (dungeon.checkIsWalkAllowed(this, getX() - 1, getY())) {
            setPosition(getX() - 1, getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean moveRight() {
        if (dungeon.checkIsWalkAllowed(this, getX() + 1, getY())) {
            setPosition(getX() + 1, getY());
            return true;
        }
        return false;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x().set(x);
        this.y().set(y);
    }

    @Override
    public void update(Player p) {
        if (p.invincible == true){
            this.setStrategy(scared);
        } else {
            this.setStrategy(aggressive);
        }
        this.move(p, this);
        this.checkDeath(p);
    }

	@Override
	public boolean ableUnlockDoor(Door door) {
		// enemies cannot use doors
		return false;
	}
    @Override
    public void move(Player p, Enemy e) {
        this.getStrategy().move(p, e);
    }

    public void checkDeath(Player p){
        if (p.attacking){
            if (Math.abs(this.getX() - p.getX()) == 1 && Math.abs(this.getY() - p.getY()) == 0 ||
                Math.abs(this.getX() - p.getX()) == 0 && Math.abs(this.getY() - p.getY()) == 1){
                    dungeon.removeEntity(this); // kill the enemy
                    p.useSwordSwing();
                    return;
            }
        }
        if (p.isInvincible() && this.getX() == p.getX() && this.getY() == p.getY()){
            dungeon.removeEntity(this); // kill enemy
            return;
        }
        if (!p.isInvincible() && this.getX()==p.getX() && this.getY() == p.getY()){
            dungeon.removeEntity(p); // kill player
            dungeon.setPlayer(null);
            return;
        }
    }


}