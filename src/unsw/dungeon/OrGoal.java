package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class OrGoal implements Goal{

    private List<Goal> goal = new ArrayList<Goal>();

    public OrGoal() {
        this.goal = new ArrayList<>();
	}

    @Override
    public boolean achievedGoal() {
        return true;
    }

    public void add(Goal goal) {
        this.goal.add(goal);
    }

    @Override
    public String toString() {
        int size = goal.size();
        String result = "";
        for (Goal g: goal) {
            if (result.equals("")) {
                result = "(" + g.toString();
            } else {
                result = result + " OR " + g.toString();
            }
        }
        result += " )";
        return result;
    }
}
