import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.lang.*;

/**
 * Created by sri on 18/4/15.
 */
public class FeatureExtraction {
    // Extracts the features for each character
    BufferedImage img;
    Pixel start;
    Pixel end;
    int hDist; // Horizontal distances between the grids
    int vDist; // Vertical distances between the grids
    HashMap<Integer, Grid> feat;

    FeatureExtraction(BufferedImage image, PixelCharacter point) {
        img = image;
        start = point.start;
        end = point.end;

        hDist = (end.i - start.i) / Grid.numHGrid;
        vDist = (end.j - start.j) / Grid.numVGrid;

        feat = new HashMap<Integer, Grid>();
        for(int i = start.i; i <= end.i; i++) {
            for(int j = start.j; j <= end.j; j++) {
                Color c = new Color(img.getRGB(i, j));
                if(c != Color.white) {
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
    }

    void display() {
        for(Integer keys : feat.keySet()) {
            System.out.println(Double.toHexString(feat.get(keys).angle));
        }
    }

    String getByteArray() {
        StringBuilder featVect = new StringBuilder();
        int count = 0;
        for(Integer key : feat.keySet()) {
            Grid obj = feat.get(key);
            Double ang = feat.get(key).angle;
            featVect.append(obj.gridNum);
            featVect.append('\n');
            featVect.append(Double.toHexString(feat.get(key).angle));
            featVect.append('\n');
        }
        return featVect.toString();
    }

    int calcGrid(int i, int j) {
        // Calcs the grid number
        int h = (i - start.i) / hDist;
        int v = (j - start.j) / vDist;
        return new Integer(h * 10 + v);
    }
}
