package unsw.dungeon;

public class SimpleGoal implements Goal{

    private String name;

    public SimpleGoal(String name) {
		this.name = name;
	}

    public boolean achievedGoal() {
        return true;
    }

}
