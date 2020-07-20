package unsw.dungeon;

public class OrGoal extends CompositeGoal{

    /**
     *  if either node in subtree is complete return true
     * */    
    @Override
    public boolean isComplete(Dungeon dungeon) {
        return left.isComplete(dungeon) || right.isComplete(dungeon);
    }

}
