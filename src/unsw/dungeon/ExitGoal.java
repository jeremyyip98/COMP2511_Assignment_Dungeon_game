package unsw.dungeon;

public class ExitGoal implements ComponentGoal  {

    /**
     * true if the player is ontop of exit tile 
     */
    @Override
    public boolean achievedGoal(Dungeon dungeon) {
        return dungeon.isExitComplete();
    }
    
    @Override
    public String toString(){
        return "Reach the exit tile";
    }
}
