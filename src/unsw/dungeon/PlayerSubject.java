// // https://www.tutorialspoint.com/design_pattern/observer_pattern.html

// package unsw.dungeon;

// import java.util.ArrayList;
// import java.util.List;

// public class PlayerSubject {
//     private Dungeon dungeon;

//     /**
//      * 
//      * @param d
//      */
//     public PlayerSubject(Dungeon d){
//         this.dungeon = d;
//     }

//     /**
//      * List of observers for playerSubject
//      */
//     List<PlayerObserver> observers = new ArrayList<>();

//     /**
//      * subscribe/attach observer to list of observers
//      * @param o
//      */
//     public void attach(PlayerObserver o){
//         if (!observers.contains(o)){
//             observers.add(o);
//         }
//     }

//     public void detach(PlayerObserver o){
//         observers.add(o);
//     }
    
//     public void notifyObsevers(){
//         for (PlayerObserver observe : this.observers){
//             observe.update(dungeon.getPlayer());
//         }
//     }
// }