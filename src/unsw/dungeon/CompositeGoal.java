package unsw.dungeon;

public interface CompositeGoal extends ComponentGoal{
    /**
     * add a component to create a composite 
     * @param c
     */
    public void add(ComponentGoal c);
}
    
