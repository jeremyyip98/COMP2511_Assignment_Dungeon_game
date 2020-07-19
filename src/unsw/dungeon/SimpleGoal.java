package unsw.dungeon;

public class SimpleGoal implements Goal{

    private String name;

    public SimpleGoal(String name) {
		this.name = name;
	}

    @Override
    public boolean achievedGoal() {
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
