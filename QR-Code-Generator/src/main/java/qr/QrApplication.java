package qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class QrApplication {
    public static void main(String args[]) throws WriterException, IOException, NotFoundException
    {
        String inputFilePath = "/Users/adanish/QR-Code-Generator/QR-Code-Generator/files/inbound/FKATAK 02 100297600.csv";
        String data = "";
        Scanner scanner = new Scanner(new FileInputStream(inputFilePath));

        String[] header = scanner.next().split(",");
        int headerIndex = 1;

        while (scanner.hasNext()) {
            String row = scanner.next();
            List<String> tokens = Arrays.asList(row.split(",",-1));
            for (int i=1; i<tokens.size(); i++) {
                data = data.concat(header[headerIndex++]).concat(": ").concat(tokens.get(i)).concat("\n");
            }
            String path = "/Users/adanish/QR-Code-Generator/QR-Code-Generator/files/outbound/".concat(tokens.get(0)).concat(".png");
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            generateQRcode(data, path, charset, hashMap, 500, 500);//increase or decrease height and width accodingly
            System.out.println("QR Code created successfully.");
            headerIndex = 1;
            data = "";
        }
        Path archiveFilePath = Paths.get("/Users/adanish/QR-Code-Generator/QR-Code-Generator/files/archive_inbound/FKATAK 02 100297600.csv");
        Files.move(Paths.get(inputFilePath), archiveFilePath);
    }

    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w)
            throws WriterException, IOException
    {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
    }
}
