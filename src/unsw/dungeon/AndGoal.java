package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class AndGoal implements Goal{

    private List<Goal> goal = new ArrayList<Goal>();

    public AndGoal() {
        this.goal = new ArrayList<>();
	}

    public boolean achievedGoal() {
        return true;
    }

    public void add(Goal goal) {
        this.goal.add(goal);
    }

}
