package unsw.dungeon;

public class EnemyAggressive implements EnemyStrategy{

    @Override
    public void move(Player p, Enemy e) {
        int dx = p.getX() - e.getX();
        int dy = p.getY() - e.getY();
        //System.out.println("dx: " + dx + "     dy: " + dy);

        if (dx > 0 && dy > 0){
            //top right quadrant
            if (e.moveRight()) return;
            if (e.moveDown()) return;
            if (e.moveUp()) return;
            if (e.moveLeft()) return;
            return;
        }
        if (dx < 0 && dy > 0) {
            // top left quadrant
            if (e.moveDown()) return;
            if (e.moveLeft()) return;
            if (e.moveUp()) return;
            if (e.moveRight()) return;
            return;
        }
        if (dx > 0 && dy < 0) {
            // bottom right quadrant
            if (e.moveUp()) return;
            if (e.moveRight()) return;
            if (e.moveDown()) return;
            if (e.moveLeft()) return;
            return;
        }
        if (dx < 0 && dy < 0) {
            // bottom left quadrant
            if (e.moveUp()) return;
            if (e.moveLeft()) return;
            if (e.moveDown()) return;
            if (e.moveRight()) return;
            return;
        }

        if (dx == 0 || dy == 0){
            if (dx > 0 && e.moveRight()) return;
            if (dx < 0 && e.moveLeft()) return;
            if (dy > 0 && e.moveDown()) return;
            if (dy < 0 && e.moveUp()) return;
            // exhaust options
            if (dx > 0 && e.moveDown()) return;
            if (dx > 0 && e.moveUp()) return;
            if (dx > 0 && e.moveLeft()) return;

            if (dx < 0 && e.moveUp()) return; 
            if (dx < 0 && e.moveDown()) return;
            if (dx < 0 && e.moveRight()) return;

            if (dy > 0 && e.moveRight()) return;
            if (dy > 0 && e.moveLeft()) return;
            if (dy > 0 && e.moveUp()) return;

            if (dy < 0 && e.moveLeft()) return;
            if (dy < 0 && e.moveRight()) return;
            if (dy < 0 && e.moveDown()) return;

        }

        return;


    }

}