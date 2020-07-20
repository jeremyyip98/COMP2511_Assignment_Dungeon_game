package unsw.dungeon;

public class SwitchesGoal implements ComponentGoal {

    /**
     * true if all switches have been activated by boulders
     */
    @Override
    public boolean isComplete(Dungeon dungeon) {
        return (dungeon.switchComplete());
    }
    
    
}