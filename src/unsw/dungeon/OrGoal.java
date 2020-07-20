package unsw.dungeon;

public class OrGoal extends CompositeGoal{

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return left.isComplete(dungeon) || right.isComplete(dungeon);
    }

}
