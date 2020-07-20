package unsw.dungeon;

public interface ComponentGoal {

    /**
     * true if goal is done
     * @return
     */
    public abstract boolean isComplete(Dungeon dungeon);

    /**
     * convert the goal to a string
     * @return
     */
    public abstract String toString();
}
