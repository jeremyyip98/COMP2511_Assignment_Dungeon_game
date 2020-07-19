package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class OrGoal implements Goal{

    private List<Goal> goal = new ArrayList<Goal>();

    public OrGoal() {
        this.goal = new ArrayList<>();
	}

    public boolean achievedGoal() {
        return true;
    }

    public void add(Goal goal) {
        this.goal.add(goal);
    }

}
