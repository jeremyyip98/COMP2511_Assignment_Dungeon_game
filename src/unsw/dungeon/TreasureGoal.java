package unsw.dungeon;

public class TreasureGoal implements ComponentGoal {

    /**
     * true if player is holding all available treasures
     */
    @Override
    public boolean achievedGoal(Dungeon dungeon) {
        return (dungeon.getPlayer().getTreasure() == dungeon.getTreasures());
    }

    @Override
    public String toString(){
        return "Collect all treasures";
    }
}
