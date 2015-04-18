import java.util.HashMap;

/**
 * Created by sri on 16/4/15.
 */
public class Features {
    // CLASS MIGHT BE REDUNDANT
    /*
        Hashmap stores the grid number as key
        The object is a grid object with a slope and the grid number in the value
     */
    HashMap<Integer, Grid> feat;

    Features() {
        feat = new HashMap<Integer, Grid>();
    }
    void add(int gridNum, Grid obj) {
        feat.put(gridNum, new Grid(obj)); // Possible memory hog
    }

    Grid getGrid(int num) {
        Grid obj = feat.get(num);
        return obj;
    }
}
