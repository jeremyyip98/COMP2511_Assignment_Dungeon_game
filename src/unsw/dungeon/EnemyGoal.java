package unsw.dungeon;

public class EnemyGoal implements ComponentGoal{

    @Override
    public boolean achievedGoal(Dungeon dungeon) {
        // if enemies = 0 then all are dead
        return dungeon.getEnemies() <= 0;
    }
 
    @Override
    public String toString(){
        return "Kill all enemies";
    }
}