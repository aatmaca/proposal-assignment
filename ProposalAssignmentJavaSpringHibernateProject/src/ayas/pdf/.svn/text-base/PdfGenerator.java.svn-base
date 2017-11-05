/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import ayas.business.DataService;
import ayas.util.Constants;
import com.lowagie.text.Image;

/**
 *
 * @author abdullah
 */
public class PdfGenerator {

    public static byte[] pdfDraft(String webInfPath, DataService dataService) throws DocumentException, IOException {
        byte[] byteArray = null;
        Document document = new Document();
        BaseFont fnt = BaseFont.createFont(webInfPath + "/resources/arial.ttf", "iso-8859-9", BaseFont.EMBEDDED);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        PdfPageEvent events = new PdfPageEvent();
        events.setBaseFont(fnt);
        writer.setPageEvent(events);

        Font fnPageHeader = new Font(fnt, 11, Font.BOLD);
        Font fnHeader = new Font(fnt, 8, Font.BOLD);
        Font fn = new Font(fnt, 9);

        document.open();

        Paragraph tempParagraph = new Paragraph("Tarih : " + Constants.formatter.format(new Date()), fn);
        tempParagraph.setFirstLineIndent(10);
        tempParagraph.setAlignment(Chunk.ALIGN_RIGHT);
        document.add(tempParagraph);
        document.add(Chunk.NEWLINE);

        String header = "LİSTE";
        tempParagraph = new Paragraph(header, fnPageHeader);
        tempParagraph.setAlignment(Chunk.ALIGN_CENTER);
        document.add(tempParagraph);

        document.add(Chunk.NEWLINE);

        float[] widths = {2f, 10f};
        PdfPTable pageHeaderTable = new PdfPTable(widths);
        pageHeaderTable.setWidthPercentage((float) 100.0);

        pageHeaderTable.addCell(prepareNewCell("Grubu ", fn));
        pageHeaderTable.addCell(prepareNewCell(": ", fn));
        pageHeaderTable.addCell(prepareNewCell("Yıl / Dönem ", fn));
        pageHeaderTable.addCell(prepareNewCell(": ", fn));

        pageHeaderTable.addCell(prepareNewCell("Adı ", fn));
        pageHeaderTable.addCell(prepareNewCell(": ", fn));
        pageHeaderTable.addCell(prepareNewCell("Tarihi ", fn));
        pageHeaderTable.addCell(prepareNewCell(": ", fn));

        document.add(pageHeaderTable);

        document.close();
        byteArray = baos.toByteArray();
        baos.close();
        writer.close();

        return byteArray;
    }
    private static PdfPCell prepareNewCell(String value, Font fn) {
        PdfPCell cell = new PdfPCell();
        Paragraph p = prepareNewParagraph(value, fn, Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setBorderWidth(0);
        return cell;
    }

    private static PdfPCell prepareNewCellWithHeight(String value, Font fn, float height, int colspan, int borderWidth) {
        PdfPCell cell = new PdfPCell();
        Paragraph p = prepareNewParagraph(value, fn, Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setColspan(colspan);
        cell.setBorderWidth(borderWidth);
        cell.setMinimumHeight(height);
        return cell;
    }

    private static PdfPCell prepareNewCellWithBottomBorderValues(String value, Font fn, int bottomBorderWidth) {
        PdfPCell cell = new PdfPCell();
        Paragraph p = prepareNewParagraph(value, fn, Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setBorderWidthBottom(bottomBorderWidth);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    private static PdfPCell prepareNewCell(String value, Font fn, int horizontalAlignment) {
        PdfPCell cell = new PdfPCell();
        Paragraph p = prepareNewParagraph(value, fn, horizontalAlignment);
        cell.addElement(p);
        cell.setBorderWidth(0);
        return cell;
    }

    public static PdfPCell prepareNewCell(String value, Font fn, int colspan, int borderWidth) {
        PdfPCell cell = prepareNewCell(value, fn);
        cell.setColspan(colspan);
        cell.setBorderWidth(borderWidth);
        return cell;
    }

    public static PdfPCell prepareNewCell(String value, Font fn, int horizontalAlignment, int verticalAlignment, int colspan, int borderWidth) {
        PdfPCell cell = prepareNewCell(value, fn, horizontalAlignment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setColspan(colspan);
        cell.setBorderWidth(borderWidth);
        return cell;
    }

    private static Paragraph prepareNewParagraph(String value, Font fn, int horizontalAlignment) {
        Paragraph p = new Paragraph(value, fn);
        p.setAlignment(horizontalAlignment);
        return p;
    }
    public static byte[] preparePicture(String pictureUrl,String webInfPath) throws Exception{
        byte[] byteArray = null;
        Document document = new Document();
        BaseFont fnt = BaseFont.createFont(webInfPath + "/resources/arial.ttf", "iso-8859-9", BaseFont.EMBEDDED);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        Image image = Image.getInstance ("devi.jpg");
        document.add(image);

        document.close();
        byteArray = baos.toByteArray();
        baos.close();
        writer.close();
        return byteArray;
    }
}
