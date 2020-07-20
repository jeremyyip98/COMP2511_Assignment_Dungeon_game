package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.json.JSONObject;
import org.json.JSONArray;

import unsw.dungeon.*;

public class MattTest {
    @Test
    public void wallTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "wall"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "wall"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Right");

        // Check if player stays on the initial position, since it's not possible for him to walk
        assert(player.getX() == 0);
        assert(player.getY() == 0);
    }

    @Test
    public void exitTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "exit"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "exit"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Right");

        // Check if the player has entered the exit
        assert(dungeon.isExitComplete());
        
        // Need to add if Goal completed or not
        
    }

    @Test
    public void boulderTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 1).put("type", "boulder"));

        JSONObject maze = new JSONObject()
            .put("width", 4)
            .put("height", 4)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "boulder"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        Boulder boulder = new Boulder(dungeon, 1, 1);
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Boulder) {
                boulder = (Boulder) e;
            }
        }
        
        controller.handleMovement("Down");

        assert(boulder.getX() == 1);
        assert(boulder.getY() == 2);
        
        controller.handleMovement("Left");
        controller.handleMovement("Down");
        controller.handleMovement("Right");

        assert(boulder.getX() == 2);
        assert(boulder.getY() == 2);
        
        controller.handleMovement("Down");
        controller.handleMovement("Right");
        controller.handleMovement("Up");

        assert(boulder.getX() == 2);
        assert(boulder.getY() == 1);
        
        controller.handleMovement("Right");
        controller.handleMovement("Up");
        controller.handleMovement("Left");

        // Check if the boulder back to its original position
        assert(boulder.getX() == 1);
        assert(boulder.getY() == 1);
    }

    @Test
    public void twoBoulderTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 1).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 1).put("type", "boulder"))
                .put(new JSONObject().put("x", 2).put("y", 1).put("type", "boulder"));

        JSONObject maze = new JSONObject()
            .put("width", 4)
            .put("height", 3)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "boulder"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        Boulder boulder1 = new Boulder(dungeon, 2, 1);
        Boulder boulder2 = new Boulder(dungeon, 1, 1);
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Boulder) {
                boulder1 = (Boulder) e;
            }
        }

        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Boulder) {
                if (e.getX() != boulder1.getX() || e.getY() != boulder1.getY()){
                    boulder2 = (Boulder) e;
                }
            }
        }
        
        controller.handleMovement("Right");

        // Check if the boulders stayed in their original positions
        // Since player is not able to push two boulders at the same time
        assert(boulder1.getX() == 2);
        assert(boulder1.getY() == 1);

        assert(boulder2.getX() == 1);
        assert(boulder2.getY() == 1);

        controller.handleMovement("Down");
        controller.handleMovement("Right");
        controller.handleMovement("Right");
        controller.handleMovement("Right");
        controller.handleMovement("Up");
        controller.handleMovement("Left");

        // Check if the boulders stayed in their original positions
        // Since player is not able to push two boulders at the same time
        assert(boulder1.getX() == 2);
        assert(boulder1.getY() == 1);

        assert(boulder2.getX() == 1);
        assert(boulder2.getY() == 1);
    }

    @Test
    public void switchTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "switch"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "boulder"))
                .put(new JSONObject().put("x", 1).put("y", 1).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 2).put("type", "boulder"))
                .put(new JSONObject().put("x", 2).put("y", 2).put("type", "switch"));

        JSONObject maze = new JSONObject()
            .put("width", 3)
            .put("height", 3)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "boulders"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Right");
        controller.handleMovement("Up");
        controller.handleMovement("Left");

        // the switch won't be completed yet, since the player have only triggered one switch at the moment
        assert(dungeon.switchComplete() == false);

        controller.handleMovement("Down");
        controller.handleMovement("Left");
        controller.handleMovement("Down");
        controller.handleMovement("Right");

        // the switch won't be completed yet, since the player have only triggered one switch at the moment
        assert(dungeon.switchComplete());
        
        // Need to add if Goal completed or not
        
    }

    @Test
    public void enemyAggressiveTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 5).put("y", 0).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 5)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "enemies"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();
        Enemy enemy = new Enemy(dungeon, 5, 0);
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemy = (Enemy) e;
            }
        }

        controller.handleMovement("Right");

        // Check if the enemy walking towards the player
        assert(enemy.getX() == 4);

        controller.handleMovement("Right");

        // Check if the enemy walking towards the player
        assert(enemy.getX() == 3);

        controller.handleMovement("Right");

        // Check if the enemy stay still since the player is walking to his current position
        assert(enemy.getX() == 3);

        boolean playerDead = true;
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Player) {
                playerDead = false;
            }
        }
        // Check if the player is dead or not (Entity removed from dungeon)
        assert(playerDead);
        assert(dungeon.getPlayer() == null);
    }

    @Test
    public void multipleEnemyAggressiveTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 3).put("type", "enemy"))
                .put(new JSONObject().put("x", 3).put("y", 0).put("type", "enemy"))
                .put(new JSONObject().put("x", 3).put("y", 6).put("type", "player"))
                .put(new JSONObject().put("x", 6).put("y", 3).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 7)
            .put("height", 7)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "enemies"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();
        Enemy enemy1 = new Enemy(dungeon, 6, 3);
        Enemy enemy2 = new Enemy(dungeon, 3, 0);
        Enemy enemy3 = new Enemy(dungeon, 0, 3);
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemy1 = (Enemy) e;
            }
        }

        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                if (e.getX() != enemy1.getX() || e.getY() != enemy1.getY()){
                    enemy2 = (Enemy) e;
                }
            }
        }

        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                if (e.getX() == enemy1.getX() && e.getY() == enemy1.getY()){
                } else if (e.getX() == enemy2.getX() && e.getY() == enemy2.getY()){     
                } else {
                    enemy3 = (Enemy) e;
                }
            }
        }
        controller.handleMovement("Up");

        // Check if all the enemies walking towards the player
        assert(enemy1.getX() == 5);
        assert(enemy2.getY() == 1);
        assert(enemy3.getX() == 1);

        controller.handleMovement("Up");

        // Check if all the enemies walking towards the player
        assert(enemy1.getX() == 4);
        assert(enemy2.getY() == 2);
        assert(enemy3.getX() == 2);

        controller.handleMovement("Up");

        /*
        System.out.println("Enemy1: ");
        System.out.println("x is "+ enemy1.getX());
        System.out.println("y is "+ enemy1.getY());

        System.out.println("Enemy2: ");
        System.out.println("x is "+ enemy2.getX());
        System.out.println("y is "+ enemy2.getY());

        System.out.println("Enemy3: ");
        System.out.println("x is "+ enemy3.getX());
        System.out.println("y is "+ enemy3.getY());
        */
        // Check if all the enemies walking towards the player
        assert(enemy1.getX() == 3);
        assert(enemy2.getY() == 3);
        assert(enemy3.getX() == 3);

        boolean playerDead = true;
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Player) {
                playerDead = false;
            }
        }
        // Check if the player is dead or not (Entity removed from dungeon)
        assert(playerDead);
        assert(dungeon.getPlayer() == null);
    }

    @Test
    public void swordTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "enemies"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        player.addSwordSwings();
        controller.handleMovement("Space");

        //assert(player.getSwordSwings() == 4);
        boolean enemyDead = true;
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemyDead = false;
            }
        }

        // Check if the enemy is dead or not (Entity removed from dungeon)
        assert(enemyDead);
        
        // Need to add if Goal completed or not
        
    }

    @Test
    public void swordSwingTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "enemies"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        player.addSwordSwings();
        controller.handleMovement("Space");

        // Check if the remaining swordSwings decreased
        assert(player.getSwordSwings() == 4);
        
    }

    @Test
    public void swordPickUpAndAttackTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "sword"))
                .put(new JSONObject().put("x", 3).put("y", 0).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 4)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "enemies"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Right");
        controller.handleMovement("Space");
        boolean enemyDead = true;
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemyDead = false;
            }
        }

        // Check if the enemy is dead or not (Entity removed from dungeon)
        assert(enemyDead);
        
        // Need to add if Goal completed or not
        
    }

    @Test
    public void portalTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("id", 1).put("type", "portal"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 2).put("y", 2).put("id", 1).put("type", "portal"));

        JSONObject maze = new JSONObject()
            .put("width", 3)
            .put("height", 3)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "portal"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Left");

        // Check if player teleports to the corresponding portal
        assert(player.getX() == 2);
        assert(player.getY() == 2);

        controller.handleMovement("Left");

        // Check if player can move out of the portal in the correct position
        assert(player.getX() == 1);
        assert(player.getY() == 2);
    }

    @Test
    public void multiplePortalTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("id", 1).put("type", "portal"))
                .put(new JSONObject().put("x", 0).put("y", 4).put("id", 2).put("type", "portal"))
                .put(new JSONObject().put("x", 2).put("y", 4).put("type", "player"))
                .put(new JSONObject().put("x", 4).put("y", 0).put("id", 2).put("type", "portal"))
                .put(new JSONObject().put("x", 4).put("y", 4).put("id", 1).put("type", "portal"));

        JSONObject maze = new JSONObject()
            .put("width", 5)
            .put("height", 5)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "portal"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Left");
        controller.handleMovement("Left");

        // Check if player teleports to the corresponding portal
        assert(player.getX() == 4);
        assert(player.getY() == 0);

        controller.handleMovement("Left");
        controller.handleMovement("Left");
        controller.handleMovement("Left");
        controller.handleMovement("Left");

        // Check if player can move out of the portal in the correct position
        assert(player.getX() == 4);
        assert(player.getY() == 4);

        controller.handleMovement("Left");
        controller.handleMovement("Left");

        // Check if player goes back to his initial position
        assert(player.getX() == 2);
        assert(player.getY() == 4);
    }

    @Test
    public void potionTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 3).put("y", 0).put("type", "invincibility"))
                .put(new JSONObject().put("x", 6).put("y", 0).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 8)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "potion"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();
        Enemy enemy = new Enemy(dungeon, 6, 0);
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemy = (Enemy) e;
            }
        }

        controller.handleMovement("Right");

        // Check if player stays on the initial position, since it's not possible for him to walk
        assert(enemy.getX() == 5);
        assert(enemy.getY() == 0);

        controller.handleMovement("Right");

        // Player picks up the potion and the enemy start walking away from him
        assert(enemy.getX() == 6);
        assert(enemy.getY() == 0);

        controller.handleMovement("Right");

        // Player still has the potion effect and the enemy still walking away from him
        assert(enemy.getX() == 7);
        assert(enemy.getY() == 0);
    }

    @Test
    public void potionInvincibleTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "enemy"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "potion"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        // Give player a potion effect
        player.setInvincible();
        controller.handleMovement("Right");

        boolean enemyDead = true;
        for (Entity e: dungeon.getEntity()) {
            if (e instanceof Enemy) {
                enemyDead = false;
            }
        }

        // Check if the enemy is dead or not (Entity removed from dungeon)
        assert(enemyDead);
        
        // Need to add if Goal completed or not
    }

    @Test
    public void potionTimeTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));

        JSONObject maze = new JSONObject()
            .put("width", 2)
            .put("height", 2)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "potion"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        // Give player a potion effect, which can only last for 10 moves (ticks)
        player.setInvincible();

        controller.handleMovement("Right");
        controller.handleMovement("Down");
        controller.handleMovement("Left");
        controller.handleMovement("Up");
        controller.handleMovement("Right");
        controller.handleMovement("Down");
        controller.handleMovement("Left");
        controller.handleMovement("Up");
        controller.handleMovement("Right");
        controller.handleMovement("Down");


        // Check if the enemy is dead or not (Entity removed from dungeon)
        assert(player.getPotionTicks() == 0);
        
        // Need to add if Goal completed or not
    }

    @Test
    public void lockedDoorTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("id", 1).put("type", "door"))
                .put(new JSONObject().put("x", 2).put("y", 0).put("id", 1).put("type", "key"));

        JSONObject maze = new JSONObject()
            .put("width", 3)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "wall"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        System.out.println("Player: ");
        System.out.println("x is "+ player.getX());
        System.out.println("y is "+ player.getY());

        controller.handleMovement("Right");
        
        System.out.println("Player: ");
        System.out.println("x is "+ player.getX());
        System.out.println("y is "+ player.getY());

        // Check if player stays on the initial position, since it's not possible for him to walk through the locked door
        assert(player.getX() == 0);
        assert(player.getY() == 0);
    }

    @Test
    public void treasureTest() throws FileNotFoundException {
        JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "treasure"))
                .put(new JSONObject().put("x", 2).put("y", 0).put("type", "treasure"))
                .put(new JSONObject().put("x", 3).put("y", 0).put("type", "treasure"));

        JSONObject maze = new JSONObject()
            .put("width", 4)
            .put("height", 1)
            .put("entities", entities)
            .put("goal-condition", new JSONObject().put("goal", "potion"));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
        DungeonController controller = dungeonLoader.loadController();
        Dungeon dungeon = controller.getDungeon();
        Player player = dungeon.getPlayer();

        controller.handleMovement("Right");

        // Check if the player collects the treasure or not
        assert(player.getTreasure() == 1);

        controller.handleMovement("Right");
        controller.handleMovement("Right");
        
        // Check if the player collects all treasure or not
        assert(player.getTreasure() == 3);

        // Need to add if Goal completed or not
    }

    @Test
    void movementTest() throws FileNotFoundException {
            JSONArray entities = new JSONArray()
                .put(new JSONObject().put("x", 0).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 4).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 10).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 12).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 18).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 0).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 1).put("type", "player"))
                .put(new JSONObject().put("x", 2).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 18).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 1).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 4).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 10).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 12).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 2).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 14).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 3).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 4).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 14).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 18).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 5).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 6).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 12).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 7).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 8).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 9).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 10).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 10).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 11).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 12).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 13).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 14).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 4).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 15).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 18).put("y", 16).put("type", "exit"))
                .put(new JSONObject().put("x", 19).put("y", 16).put("type", "wall"))
                .put(new JSONObject().put("x", 0).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 1).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 2).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 3).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 4).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 5).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 6).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 7).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 8).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 9).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 10).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 11).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 12).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 13).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 14).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 15).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 16).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 17).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 18).put("y", 17).put("type", "wall"))
                .put(new JSONObject().put("x", 19).put("y", 17).put("type", "wall"));
                



            JSONObject maze = new JSONObject()
                .put("width", 20)
                .put("height", 18)
                .put("entities", entities)
                .put("goal-condition", new JSONObject().put("goal", "exit"));

            DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);
            DungeonController controller = dungeonLoader.loadController();
            Dungeon dungeon = controller.getDungeon();
            Player player = dungeon.getPlayer();

            controller.handleMovement("Down");
            controller.handleMovement("Down");

            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            
            controller.handleMovement("Down");
            controller.handleMovement("Right");
            controller.handleMovement("Down");
            controller.handleMovement("Right");

            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");

            controller.handleMovement("Right");
            controller.handleMovement("Right");

            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");

            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");
            controller.handleMovement("Up");

            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");
            controller.handleMovement("Right");

            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");
            controller.handleMovement("Down");

            // Check if the player has entered the exit
            assert(dungeon.isExitComplete());
            // Need to add if Goal completed or not
    }
}

