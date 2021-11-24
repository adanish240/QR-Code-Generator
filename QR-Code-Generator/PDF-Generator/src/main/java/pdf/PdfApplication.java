package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.PngImageData;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class PdfApplication extends PngImageData{
    public static final String URL = "www.ttac.ir";

    public static final String NEW_LINE = "\n";

    public static final String TAB = "\t\t";

    public static final int PADDING = 30;

    public static final int BOTTOM_PADDING = 37;

    public static final int TOP_PADDING = 0;

    public static final int RIGHT_PADDING_QR = 0;

    public static final int RIGHT_PADDING_MATTER = 10;

    private static final float LEFT_PADDING_QR = 5;


    protected PdfApplication(byte[] bytes) {
        super(bytes);
    }

    protected PdfApplication(java.net.URL url) {
        super(url);
    }


    public static void main(String args[]) throws IOException {
        String file = "QR-Code-Generator/files/outbound/example.pdf";

        // Creating a PdfDocument object
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

        // Creating a Document object
        Document doc = new Document(pdfDoc, PageSize.A4);
        doc.setBold();

        String inputFilePath = "QR-Code-Generator/files/inbound/FKATAK 02 100297600.csv";
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(inputFilePath));
        String[] header = scanner.next().split(",");
        int headerIndex = 1;
        int imageIndex = 1;
        int pageBreakerCount = 0;

        while (scanner.hasNext()) {
            pageBreakerCount++;
            String row = scanner.next();
            List<String> tokens = Arrays.asList(row.split(",",-1));
            row = "";
            for (int i=1; i<tokens.size()-1; i++) {
                row = row.concat(header[headerIndex++])
                        .concat(":").concat(tokens.get(i)).concat(NEW_LINE);
            }
            data.add(row);
            if(data.size() == 3) {
                Table table = new Table(6);
                for (int j=0 ;j<data.size();j++) {
                    Image image = new Image(ImageDataFactory.create("QR-Code-Generator/files/outbound/" + imageIndex++ + ".png"));
                    image.setHeight(50f);
                    image.setWidth(50f);
                    table.addCell(image);
                    Text text = new Text(data.get(j));
                    text.setFontSize(7f);
                    Paragraph paragraph = new Paragraph(text);
                    paragraph.add(new Text(NEW_LINE.concat(URL)).setFontSize(5f));
                    table.addCell(paragraph).setAutoLayout();
                    removeBorders(table);

                }
                doc.add(table);
                data.clear();
                System.out.println("Text added successfully..");
            }
            if (pageBreakerCount == 24) {
                doc.add(new AreaBreak());
                pageBreakerCount = 0;
            }
            headerIndex = 1;
        }
        Path archiveFilePath = Paths.get("QR-Code-Generator/files/archive_inbound/FKATAK 02 100297600.csv");
//        Files.move(Paths.get(inputFilePath), archiveFilePath);
        doc.close();
    }

    private static void removeBorders(Table table) {
        if (Objects.nonNull(table.getCell(0,0))) {
            table.getCell(0,0).setPaddingTop(TOP_PADDING).setBorder(Border.NO_BORDER);
            table.getCell(0,0).setPaddingRight(RIGHT_PADDING_QR);
            table.getCell(0,0).setPaddingLeft(LEFT_PADDING_QR);
            table.getCell(0,0).setPaddingBottom(BOTTOM_PADDING);

        }
        if (Objects.nonNull(table.getCell(0,1))) {
            table.getCell(0,1).setBorder(Border.NO_BORDER);
            table.getCell(0,1).setPaddingRight(PADDING);
            table.getCell(0,1).setPaddingLeft(RIGHT_PADDING_MATTER);
            table.getCell(0,1).setPaddingBottom(BOTTOM_PADDING);

        }
        if (Objects.nonNull(table.getCell(0,2))) {
            table.getCell(0,2).setPaddingTop(TOP_PADDING).setBorder(Border.NO_BORDER);
            table.getCell(0,2).setPaddingRight(RIGHT_PADDING_QR);
            table.getCell(0,2).setPaddingLeft(LEFT_PADDING_QR);
            table.getCell(0,2).setPaddingBottom(BOTTOM_PADDING);


        }
        if (Objects.nonNull(table.getCell(0,3))) {
            table.getCell(0,3).setBorder(Border.NO_BORDER);
            table.getCell(0,3).setPaddingRight(PADDING);
            table.getCell(0,3).setPaddingLeft(RIGHT_PADDING_MATTER);
            table.getCell(0,3).setPaddingBottom(BOTTOM_PADDING);


        }
        if (Objects.nonNull(table.getCell(0,4))) {
            table.getCell(0,4).setPaddingTop(TOP_PADDING).setBorder(Border.NO_BORDER);
            table.getCell(0,4).setPaddingRight(RIGHT_PADDING_QR);
            table.getCell(0,4).setPaddingLeft(LEFT_PADDING_QR);
            table.getCell(0,4).setPaddingBottom(BOTTOM_PADDING);


        }
        if (Objects.nonNull(table.getCell(0,5))) {
            table.getCell(0,5).setBorder(Border.NO_BORDER);
            table.getCell(0,5).setPaddingRight(PADDING);
            table.getCell(0,5).setPaddingLeft(RIGHT_PADDING_MATTER);
            table.getCell(0,5).setPaddingBottom(BOTTOM_PADDING);


        }

    }
}