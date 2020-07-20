package unsw.dungeon;

public class AndGoal extends CompositeGoal{

    /**
     * if both the left and right nodes of tree complete return true
     * 
     */
    @Override
    public boolean isComplete(Dungeon dungeon) {
        return left.isComplete(dungeon) && right.isComplete(dungeon);
    }

}
