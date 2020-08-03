package unsw.dungeon;

public class OrGoal extends CompositeGoal{

    @Override
    public boolean achievedGoal(Dungeon dungeon) {
        return left.achievedGoal(dungeon) || right.achievedGoal(dungeon);
    }

    @Override
    public String toString(){
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }

}
