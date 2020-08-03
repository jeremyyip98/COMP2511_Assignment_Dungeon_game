package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

//import org.graalvm.compiler.lir.aarch64.AArch64Move.Move;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    // public DungeonLoader(JSONObject json) {
    //     this.json = json;
    // }

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));

        }
        dungeon.connectEntities();

        ComponentGoal goal = scanGoal(json.getJSONObject("goal-condition"));

        dungeon.setGoal(goal);

        //System.out.println(goal.toString()); // TODO remove
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(x, y);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                onLoad(wall);
                entity = wall;
                break;
            // TODO Handle other possible entities
            case "exit":
                Exit exit = new Exit(dungeon, x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "portal":
                Portal portal = new Portal(x, y, json.getInt("id"));
                onLoad(portal);
                entity = portal;
                break;
            case "switch":
                FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
                onLoad(floorSwitch);
                entity = floorSwitch;
                break;
            case "boulder":
                Boulder boulder = new Boulder(dungeon, x, y);
                onLoad(boulder);
                entity = boulder;
                break;
            case "swordman":
                Swordman swordman = new Swordman(dungeon, x, y);
                onLoad(swordman);
                entity = swordman;
                break;
            case "key":
                Key key = new Key(dungeon, x, y, json.getInt("id"));
                onLoad(key);
                entity = key;
                break;
            case "door":
                Door door = new Door(x, y, json.getInt("id"));
                onLoad(door);
                entity = door;
                break;
            case "invincibility":
                Potion potion = new Potion(dungeon, x, y);
                onLoad(potion);
                entity = potion;
                break;
            case "sword":
                Sword sword = new Sword(dungeon, x, y);
                onLoad(sword);
                entity = sword;
                break;
            case "treasure":
                Treasure treasure = new Treasure(dungeon, x, y);
                onLoad(treasure);
                entity = treasure;
                break;
            // Extra
            case "hound":
                Hound hound = new Hound(dungeon, x, y);
                onLoad(hound);
                entity = hound;
                break;
            case "movement":
                MovementPotion movementPotion = new MovementPotion(dungeon, x, y);
                onLoad(movementPotion);
                entity = movementPotion;
                break;
            case "bat":
                Bat bat = new Bat(dungeon, x, y);
                onLoad(bat);
                entity = bat;
                break;
        }    
        dungeon.addEntity(entity);
    }

    private ComponentGoal scanGoal(JSONObject obj) {
        ComponentGoal comp = null;
        JSONArray jsonSubGoals;
        String goal = obj.getString("goal");
        switch(goal){
            case "enemies":
                comp = new EnemyGoal();
                break;
            case "treasure":
                comp = new TreasureGoal();
                break;
            case "exit":
                comp = new ExitGoal();
                break;
            case "boulders":
                comp = new SwitchesGoal();
                break;
            case "AND":
                CompositeGoal and = new AndGoal();	        
                jsonSubGoals = obj.getJSONArray("subgoals");
                for (int i = 0; i < jsonSubGoals.length(); i++) 
                    and.add(scanGoal(jsonSubGoals.getJSONObject(i)));
                comp = and;
                break;
            case "OR":
                CompositeGoal or = new OrGoal();	        
                jsonSubGoals = obj.getJSONArray("subgoals");
                for (int i = 0; i < jsonSubGoals.length(); i++) 
                    or.add(scanGoal(jsonSubGoals.getJSONObject(i)));
                comp = or;
                break;
        }
        return comp;

    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // Added entities
    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(FloorSwitch Floorswitch);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Swordman swordman);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Hound hound);

    public abstract void onLoad(MovementPotion movementPotion);

    public abstract void onLoad(Bat bat);

    // TODO Create additional abstract methods for the other entities

}
