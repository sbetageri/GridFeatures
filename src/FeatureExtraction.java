import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

/**
 * Created by sri on 18/4/15.
 */
public class FeatureExtraction {
    // Extracts the features for each character
    /*
        For each pixel, the grid to which it belongs to is calculated
        Once the grid is identified, the pixel is checked to see if it is the topmost grid or the bottommost
        For each new assignment, the slope is recalculated.
     */
    BufferedImage img;
    Pixel start;
    Pixel end;
    int hDist; // Horizontal distances between the grids
    int vDist; // Vertical distances between the grids
    HashMap<Integer, Grid> feat;

    /*
        TODO
        Index the grids from 1
     */
    FeatureExtraction(ArrayList<Pixel> pix, PixelCharacter pChar) {
        // boundary of the character
        // Ha Ha, this constructor i
        start = pChar.start;
        end = pChar.end;

        hDist = (end.i - start.i) / Grid.numHGrid; // Horizontal length of each grid
        vDist = (end.j - start.j) / Grid.numVGrid; // vertical length of each grid
        System.out.println("horizontal dist : " + hDist);
        System.out.println("vertical dist : " + vDist);

        feat = new HashMap<Integer, Grid>(); // Hashmap for easy retrieval of the specific grid

        for(int i = 0; i < pix.size(); i++) {
            // For each pixel, calculate the grid number and assign it to the appropriate grid
            Pixel point = pix.get(i);
            Integer gridNum = calcGrid(point);
            if(feat.containsKey(gridNum)) {
                Grid obj = feat.get(gridNum);
                obj.assign(point);
            } else {
                Grid obj = new Grid(gridNum);
                obj.assign(point);
                feat.put(gridNum, obj);
            }
        }
    }

    FeatureExtraction(BufferedImage image, PixelCharacter point) {
        img = image;
        start = point.start;
        end = point.end;

        hDist = (end.i - start.i) / Grid.numHGrid;
        vDist = (end.j - start.j) / Grid.numVGrid;
        System.out.println("horizontal dist : " + hDist);
        System.out.println("vertical dist : " + vDist);

        feat = new HashMap<Integer, Grid>();
        int count = 0;
        for(int i = start.i; i <= end.i; i++) {
            for(int j = start.j; j <= end.j; j++) {
                Color c = new Color(img.getRGB(i, j));
                if(c != Color.white) {
                    count++;
                    // Assign it to appropriate grid
                    Integer num = calcGrid(i, j);
                    Pixel pix = new Pixel(i, j);
                    if(feat.containsKey(num)) {
                        Grid obj = feat.get(num);
                        obj.assign(pix);
                    } else {
                        Grid obj = new Grid(num);
                        obj.assign(pix);
                        feat.put(num, obj);
                    }
                }
            }
        }
        System.out.println("NUMBER OF PIXELS : " + count);
    }

    void display() {
        for(Integer keys : feat.keySet()) {
            Grid obj = feat.get(keys);
            System.out.println("gridNUm: " + obj.gridNum);
            System.out.println("slope : " + obj.slope);
            System.out.println("angle : " + obj.angle);
        }
    }

    String getArray() {
        StringBuilder featVect = new StringBuilder();
        for(Integer key : feat.keySet()) {
            Grid obj = feat.get(key);
            featVect.append(obj.gridNum);
            featVect.append(',');
            featVect.append(Double.toString(feat.get(key).angle));
            featVect.append("\r\n");
        }
        return featVect.toString();
    }

    int calcGrid(Pixel point) {
        return calcGrid(point.i, point.j);
    }

    int calcGrid(int i, int j) {
        // Calcs the grid number

        int h = (i - start.i) / hDist;
        // The horizontal distance of the pixel from the boundary
        // is then divided by the horizontal length to find the grid number

        int v = (j - start.j) / vDist;
        // Same logic as horizontal grid number

        return new Integer(h * 100 + v);
        // Has to be Integer for hashmap, primitives aren't allowed to be a key in a hashmap
        // The grid number is made up of the number of grid offsets from the start boundary
        // For more details and a better explanation, ask developer in charge
    }
}
