package unsw.dungeon;

public class TreasureGoal implements ComponentGoal {

    /**
     * true if player is holding all available treasures
     */
    @Override
    public boolean isComplete(Dungeon dungeon) {
        return (dungeon.getPlayer().getInvTreasure() == dungeon.getTreasures());
    }



    
}