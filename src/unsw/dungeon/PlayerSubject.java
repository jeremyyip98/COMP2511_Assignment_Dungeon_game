// https://www.tutorialspoint.com/design_pattern/observer_pattern.html
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public interface PlayerSubject {
    List<PlayerObserver> observers = new ArrayList<>();

}