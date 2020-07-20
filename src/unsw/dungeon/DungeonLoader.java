package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
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
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // Added entities
    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(FloorSwitch Floorswitch);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Sword sword);

    // TODO Create additional abstract methods for the other entities

}
