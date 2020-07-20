package unsw.dungeon;

public class ExitGoal implements ComponentGoal  {

    /**
     * true if the player is ontop of exit tile 
     */
    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.isExitComplete();
    }
    
}