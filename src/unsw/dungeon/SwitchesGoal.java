package unsw.dungeon;

public class SwitchesGoal implements ComponentGoal {

    /**
     * true if all switches have been activated by boulders
     */
    @Override
    public boolean achievedGoal(Dungeon dungeon) {
        return (dungeon.switchComplete());
    }

    @Override
    public String toString(){
        return "Activate Switches";
    }
    
}
