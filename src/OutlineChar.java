import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sri on 8/4/15.
 */
public class OutlineChar {
    /*
        Draws the border around the character
                    OR
        Places the boundary pixels onto an ArrayList
     */
    BufferedImage image;
    int height;
    int width;
    ArrayList<Pixel> pix;

    OutlineChar(BufferedImage obj) throws IOException {
        // Stores the boundary pixels onto an arrayList
        image = obj;
        height = obj.getHeight();
        width = obj.getWidth();
        pix = new ArrayList<Pixel>();

        BufferedImage outline = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // outline is kept here to prevent code from breaking, will be removed in production code

        for(int i = 1; i < height - 1; i++) {
            // i = 1 and < height - 1 to skip the possible edges of the image

            for (int j = 1; j < width - 1; j++) {
                // j = 1 and < width - 1 to skip the possible edges of the image

                if (isBoundaryPixel(j, i)) {
                    Pixel point = new Pixel(j, i);
                    pix.add(point);
                    outline.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    outline.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
        }
    }

    ArrayList<Pixel> getPixelList() {
        return pix;
    }

    OutlineChar(BufferedImage obj, Pixel start, Pixel end) throws IOException {
        // Draws only the boundary pixels onto a BufferedImage
        image = obj;
        height = obj.getHeight();
        width = obj.getWidth();
        pix = new ArrayList<Pixel>();
        BufferedImage outline = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = start.i; i < end.j; i++)
            for (int j = start.i; j < end.i; j++) {
                if (isBoundaryPixel(j, i)) {
                    Pixel point = new Pixel(j, i);
                    pix.add(point);
                    outline.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    outline.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
    }

    BufferedImage getImage() {
        return image;
    }

    boolean isBoundaryPixel(int i, int j) {
        // Each pixel is considered along with all the pixels that shares
        // either a common edge or a common vertex with the considered pixel
        // the centre pixel being checked for being a boundary pixel
        /*
                    012
                    345
                    678
            To check if the pixel is a boundary pixel, we check if the pixels at any of
            positions 1,3, 4 OR 5 are white.
            If any are white, then the pixel is a boundary pixel
         */
        int[] box = new int[9];
        image.getRGB(i - 1, j - 1, 3, 3, box, 0, 3);
        if(box[4] == Color.black.getRGB()) {
            if(box[1] == Color.white.getRGB() || box[3] == Color.white.getRGB()
                    ||  box[5] == Color.white.getRGB() || box[7] == Color.white.getRGB())
                return true;
        }
        return false;
    }
}
