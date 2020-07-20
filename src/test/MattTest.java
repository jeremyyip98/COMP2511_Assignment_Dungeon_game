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

        player.moveUp();
        player.moveDown();
        player.moveLeft();
        player.moveRight();

        // Check if player stays on the initial position, since it's not possible for him to walk
        assert(player.getX() == 0);
        assert(player.getY() == 0);
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
        
        player.moveDown();

        assert(boulder.getX() == 1);
        assert(boulder.getY() == 2);
        
        player.moveLeft();
        player.moveDown();
        player.moveRight();

        assert(boulder.getX() == 2);
        assert(boulder.getY() == 2);
        
        player.moveDown();
        player.moveRight();
        player.moveUp();

        assert(boulder.getX() == 2);
        assert(boulder.getY() == 1);
        
        player.moveRight();
        player.moveUp();
        player.moveLeft();

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
        System.out.println("Boulder1:");
        System.out.println(boulder1.getX());
        System.out.println(boulder1.getY());

        System.out.println("Boulder2:");
        System.out.println(boulder2.getX());
        System.out.println(boulder2.getY());
        
        player.moveRight();
        System.out.println("Player is at: [" + player.getX() + "][" + player.getY() + "]");

        // Check if the boulders stayed in their original positions
        // Since player is not able to push two boulders at the same time
        assert(boulder1.getX() == 2);
        assert(boulder1.getY() == 1);

        assert(boulder2.getX() == 1);
        assert(boulder2.getY() == 1);

        player.moveDown();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveUp();
        player.moveLeft();

        System.out.println("Player is at: [" + player.getX() + "][" + player.getY() + "]");

        // Check if the boulders stayed in their original positions
        // Since player is not able to push two boulders at the same time
        assert(boulder1.getX() == 2);
        assert(boulder1.getY() == 1);

        assert(boulder2.getX() == 1);
        assert(boulder2.getY() == 1);
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
            Exit exit = dungeon.getExit();

            player.moveDown();
            player.moveDown();

            player.moveRight();
            player.moveRight();
            player.moveRight();
            player.moveRight();
            player.moveRight();
            
            player.moveDown();
            player.moveRight();
            player.moveDown();
            player.moveRight();

            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();

            player.moveRight();
            player.moveRight();

            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();

            player.moveRight();
            player.moveRight();
            player.moveRight();
            player.moveRight();
            
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();

            player.moveRight();
            player.moveRight();
            player.moveRight();
            player.moveRight();

            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();
            player.moveDown();

            assert(player.getX() == exit.getX());
            assert(player.getY() == exit.getY());
    }
}

