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
        String source = "C:\\Users\\Sri\\Desktop\\Proj\\trial\\first.bmp"; // source of the image
        String dest = "C:\\Users\\Sri\\Desktop\\Proj\\trial\\"; // destination of the features
//        String txt = ".txt";
        String blackWhite = "BW.bmp";
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
//        for (int i = 3; i < 4; i++) {
//            StringBuilder source = new StringBuilder("C:\\Users\\Sri\\Desktop\\Proj\\trial\\");
//            source.append(i);
//            source.append("BW.bmp");
//            StringBuilder dest = new StringBuilder("C:\\Users\\Sri\\Desktop\\Proj\\trial\\");
//            dest.append(i);
//            dest.append(".txt");
            BufferedImage image = ImageIO.read(new File(source));
            ImageBlackNWhite obj = new ImageBlackNWhite(image);
            image = obj.getNewImage();
//        image = image.getSubimage(1, 1, image.getWidth() - 1, image.getHeight() - 1); // Crops extra borders
            Extract extractedChar = new Extract(image); //
        System.out.println("Num pixelcharacters : " + extractedChar.pChar.size());
        for(int i = 0; i < extractedChar.pChar.size(); i++) {
            PixelCharacter pChar = extractedChar.pChar.get(i);
            StringBuilder picDest = new StringBuilder(dest);
//            StringBuilder featDest = new StringBuilder(dest);
            picDest.append(i);
//            featDest.append(i);
            picDest.append(blackWhite);
//            featDest.append(txt);
//            pChar.showPixels();
            BufferedImage temp = image.getSubimage(pChar.start.i, pChar.start.j, pChar.getWidth(), pChar.getHeight());
            File picOut = new File(picDest.toString());
            ImageIO.write(temp, "bmp", picOut);
            FeatureExtraction trial = new FeatureExtraction(extractedChar.pix, pChar);
//            String features = trial.getArray();
//            File out = new File(dest.toString());
//            FileWriter fw = new FileWriter(out);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(features);
//            bw.close();
        }
    }
    // Extracts the features from the outline of the character

//            String features = trial.getArray();
//            File out = new File(dest);
//            FileWriter fw = new FileWriter(out);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(features);
//            bw.close();
}
