package unsw.dungeon;

public interface ComponentGoal {
    /**
     * true if the goal is completed
     * @return boolean
     */
    public abstract boolean achievedGoal(Dungeon dungeon);

    /**
     * convert the goal to a string
     * @return
     */
    public abstract String toString();
}
