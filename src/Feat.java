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
        String source = "/home/sri/p/proj/test/ka/ka96BW.bmp"; // source of the image
        String dest = "/home/sri/p/proj/featVal/ka/hexVal96.txt"; // destination of the features
        BufferedImage image = ImageIO.read(new File(source));
        image = image.getSubimage(1, 1, image.getWidth() - 1, image.getHeight() - 1); // Crops extra borders
        Extract extractedChar = new Extract(image); //
        PixelCharacter pChar = extractedChar.pChar.get(0);

        FeatureExtraction trial = new FeatureExtraction(extractedChar.pix, pChar);
        // Extracts the features from the outline of the character

        String features = trial.getArray();
        File out = new File(dest);
        FileWriter fw = new FileWriter(out);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(features);
        bw.close();
    }
}
