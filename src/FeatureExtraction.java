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
    BufferedImage img;
    Pixel start;
    Pixel end;
    int hDist; // Horizontal distances between the grids
    int vDist; // Vertical distances between the grids
    HashMap<Integer, Grid> feat;

    FeatureExtraction(ArrayList<Pixel> pix, Pixel sPix, Pixel ePix) {
        start = sPix;
        end = ePix;
        hDist = (end.i - start.i) / Grid.numHGrid;
        vDist = (end.j - start.j) / Grid.numVGrid;
        System.out.println("horizontal dist : " + hDist);
        System.out.println("vertical dist : " + vDist);

        feat = new HashMap<Integer, Grid>();
        for(int i = 0; i < pix.size(); i++) {
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
        System.out.println("Number of grids used : " + feat.size());
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
//            System.out.print("start : ");
//            obj.start.display();
//            System.out.print("end : ");
//            obj.end.display();
            System.out.println("slope : " + obj.slope);
            System.out.println("angle : " + obj.angle);
//            System.out.println(Double.toHexString(feat.get(keys).angle));
        }
    }

    String getByteArray() {
        StringBuilder featVect = new StringBuilder();
        int count = 0;
        for(Integer key : feat.keySet()) {
            Grid obj = feat.get(key);
            featVect.append(obj.gridNum);
            featVect.append('\n');
            featVect.append(Double.toString(feat.get(key).angle));
            featVect.append('\n');
            count++;
        }
        return featVect.toString();
    }

    int calcGrid(Pixel point) {
        return calcGrid(point.i, point.j);
    }

    int calcGrid(int i, int j) {
        // Calcs the grid number
        int h = (i - start.i) / hDist;
        int v = (j - start.j) / vDist;
        return new Integer(h * 100 + v);
    }
}
