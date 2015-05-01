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
        String source = "/home/sri/p/proj/test/ra.bmp"; // source of the image
        String dest = "/home/sri/p/proj/featVal/ra.txt"; // destination of the features
//        /*
//        String[] num = { "6",
//                "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
//                "17", "18", "19", "20", "21", "22", "23"};
//        String pre = "/home/sri/p/KA/image/k";
//        String iSuf = "BW.bmp";
//        String oTxt = "NEW.txt";
//        for(String k : num) {
//            StringBuilder src = new StringBuilder();
//            StringBuilder dst = new StringBuilder();
//            src.append(pre);
//            src.append(k);
//            src.append(iSuf);
//            dst.append(pre);
//            dst.append(k);
//            dst.append(oTxt);
//            String source = src.toString();
//            String dest = dst.toString();
//            */
            BufferedImage image = ImageIO.read(new File(source));
            ImageBlackNWhite obj = new ImageBlackNWhite(image);
            image = obj.getNewImage();
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
