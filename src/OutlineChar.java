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
    BufferedImage image;
    int height;
    int width;
    ArrayList<Pixel> pix;

    OutlineChar(BufferedImage obj) throws IOException {
        image = obj;
        height = obj.getHeight();
        width = obj.getWidth();
        pix = new ArrayList<Pixel>();
        BufferedImage outline = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int count = 0;
        for(int i = 1; i < height - 1; i++)
            for(int j = 1; j < width - 1; j++) {
                if(isBoundaryPixel(j, i)) {
                    Pixel point = new Pixel(j, i);
                    pix.add(point);
                    outline.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    outline.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
        System.out.println("Count : " + count);
    }

    ArrayList<Pixel> getPixelList() {
        return pix;
    }

    OutlineChar(BufferedImage obj, Pixel start, Pixel end) throws IOException {
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
