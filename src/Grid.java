/**
 * Created by sri on 16/4/15.
 */
public class Grid {
    /*
        TODO
        Change name of the start and end to appropriate ones
     */

    static int numHGrid; // number of horizontal grids   POSSIBLY A CONSTANT
    static int numVGrid; // number of vertical grids   POSSIBLY A CONSTANT

    static {
        numHGrid = 20;
        numVGrid = 20;
    }
    int gridNum; // ten's place holds the x coordinate of the grid
                 // one's place has the y coordinate
    Pixel start; // starting point of the slope
    // Also the top most point
    Pixel end;   // ending point of the slope
    // Also the bottom most point
    double slope;
    double angle;

    Grid(int num) {
        start = new Pixel(Integer.MIN_VALUE, Integer.MIN_VALUE);
        end = new Pixel(Integer.MAX_VALUE, Integer.MAX_VALUE);
        gridNum = num;
    }

    Grid() {
        start = new Pixel(Integer.MIN_VALUE, Integer.MIN_VALUE);
        end = new Pixel(Integer.MAX_VALUE, Integer.MAX_VALUE);
        gridNum = 0;
        slope = 0.0;
    }

    Grid(Grid obj) {
        gridNum = obj.gridNum;
        start = new Pixel(obj.start);
        end = new Pixel(obj.end);
        slope = obj.slope;
    }

    void calcSlope() {
        slope = (start.j - end.j) / (double)(start.i - end.i);
        angle = Math.toDegrees(Math.atan(slope));
    }

    void setGridNum(int num) {
        gridNum = num;
    }

    void assign(Pixel point) {
        if( isLower(point) || isHigher(point)) {
            calcSlope();
        }
    }

    boolean isHigher(Pixel point) {
        // True if point is higher than start
        if(point.j > start.j) {
            start = point;
            return true;
        }
        return false;
    }

    boolean isLower(Pixel point) {
        // True if point is lower than end
        if(point.j < end.j) {
            end = point;
            return true;
        }
        return false;
    }
}
