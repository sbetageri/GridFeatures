import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sri on 16/4/15.
 */
public class Feat {
    /*
        Extracts the features from the outline of the character and stores it
        in the Grid object arraylist
     */
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("/home/sri/p/proj/trial/imgs/gaBW.bmp"));
        Extract pix = new Extract(image);
        FeatureExtraction vect = new FeatureExtraction(image, pix.pChar.get(0));
        String features = vect.getByteArray();
        System.out.println(features);
        File out = new File("/home/sri/p/proj/trial/feat/hexVal.txt");
        FileWriter fw = new FileWriter(out);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(features);
        bw.close();
    }
}
