package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image portalImage;
    private Image floorSwitchImage;
    private Image boulderImage;
    private Image swordmanImage;
    private Image keyImage;
    private Image closedDoorImage;
    private Image openDoorImage; // TODO
    private Image potionImage;
    private Image swordImage;
    private Image treasureImage;

    // Extra
    private Image houndImage;
    private Image movementPotionImage;
    private Image batImage;


    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        swordmanImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
        potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        houndImage = new Image((new File("images/hound.png")).toURI().toString());
        movementPotionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        batImage = new Image((new File("images/bat.gif")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        view.setViewOrder(-1);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        view.setViewOrder(-1);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        ColorAdjust col = new ColorAdjust();
        col.setHue(portal.getId()*0.37%1);
        view.setEffect(col);
        addEntity(portal, view);
    }


    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Swordman swordman) {
        ImageView view = new ImageView(swordmanImage);
        view.setViewOrder(-0.5);
        addEntity(swordman, view);
    }

	@Override
	public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        ColorAdjust col = new ColorAdjust();
        col.setHue(key.getId()*-0.21%1);
        view.setEffect(col);
        addEntity(key, view);
    }
    
    @Override
	public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        ColorAdjust col = new ColorAdjust();
        col.setHue(door.getId()*-0.21%1);
        view.setEffect(col);
        addEntity(door, view);
    }
    
    @Override
	public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
	public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
	public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
	public void onLoad(Hound hound) {
        ImageView view = new ImageView(houndImage);
        addEntity(hound, view);
    }
    
    @Override
	public void onLoad(MovementPotion movementPotion) {
        ImageView view = new ImageView(movementPotionImage);
        addEntity(movementPotion, view);
    }
    
    @Override
	public void onLoad(Bat bat) {
        ImageView view = new ImageView(batImage);
        addEntity(bat, view);
	}

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        if (entity instanceof Door){
            Door d = (Door) entity; // cast to door type
            d.locked().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    ImageView image = (ImageView) node;
                    image.setImage(openDoorImage);
                }
            }); 
        }
        if (entity instanceof Player){
            Player p = (Player) entity;
            ImageView img = (ImageView) node;
            p.isInvincible().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    ColorAdjust col = new ColorAdjust();
                    if (newValue) {
                        col.setBrightness(0.3);
                        col.setHue(0.53);
                    } else {
                        col.setBrightness(0);
                        col.setHue(0);
                    }
                    img.setEffect(col);
                }
            }); 
            /** 
             * 
             p.getAttacking().addListener(new ChangeListener<Boolean>() {
                 @Override
                 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                     ColorAdjust col = new ColorAdjust();
                     if (newValue){
                         
                    }
                    img.setEffect(col);
                }
            }); 
            */
        }
    }


    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }



}
