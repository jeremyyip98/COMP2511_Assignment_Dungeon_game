package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class AndGoal implements Goal{

    private String name;

    private List<Goal> goal = new ArrayList<Goal>();

    public AndGoal(String name) {
        this.name = name;
        this.goal = new ArrayList<>();
	}

    public boolean achievedGoal() {
        return true;
    }

    public void add(Goal goal) {

    }

}
