package unsw.dungeon;

public class EnemyGoal implements ComponentGoal{

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.isEnemyComplete();
    }
    
}