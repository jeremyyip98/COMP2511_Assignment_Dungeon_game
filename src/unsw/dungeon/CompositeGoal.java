package unsw.dungeon;

public abstract class CompositeGoal implements ComponentGoal{
    // tree data structure
    ComponentGoal left;
    ComponentGoal right;

    /**
     * add a component to create a composite 
     * @param c
     */
    public void add(ComponentGoal c) {
        if (left == null){
            left = c;
        } else {
            right = c;
        }
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "   " + this.toString() + "   " + right.toString() + ")";
    }

}
