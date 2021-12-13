import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.TextAlignment;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BarCodeApplication {

    public static void main(String[] args) {
        int init = 71041001;
        String barCodeData = "T";
        for (int i=0; i<1500; i++) {
//            String number = Long.toString(init);
//            for (int j =0 ; j<number.length(); j++) {
//                barCodeData = barCodeData.concat(" ").concat(Character.toString(number.charAt(j)));
//            }
            barCodeData = barCodeData.concat(Integer.toString(init));
            BarCodeApplication.createImage(Integer.toString(i).concat(".png"), barCodeData);
            init = init+1;
            barCodeData = "T";
        }
        System.out.println("finished");
    }
    public static void createImage(String image_name,String myString)  {
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setBarHeight(4f);
            code128.setModuleWidth(0.22);
            code128.setQuietZone(1);
//            code128.setVerticalQuietZone(2);
            code128.setFontSize(1f);
            code128.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
            code128.doQuietZone(false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 500, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            //write to png file
            FileOutputStream fos = new FileOutputStream("/Users/adanish/QR-Code-Generator/QR-Code-Generator/files/outbound/"+image_name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
