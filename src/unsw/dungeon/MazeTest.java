package unsw.dungeon;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

class MazeTest {

        @Test
        void mazeTest() {
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
                .put(new JSONObject().put("x", 1).put("y", 1).put("type", "wall"))
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
                .put(new JSONObject().put("x", 18).put("y", 16).put("type", "wall"))
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
            Dungeon dungeon = dungeonLoader.load();
            Player player = dungeon.getPlayer();

            
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

            
    }
}