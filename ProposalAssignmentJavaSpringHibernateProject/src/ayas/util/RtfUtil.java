/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.direct.RtfDirectContent;
import com.lowagie.text.rtf.table.RtfBorder;
import com.lowagie.text.rtf.table.RtfBorderGroup;
import com.lowagie.text.rtf.table.RtfCell;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class RtfUtil {

    public static byte[] rtfDraft(String webInfPath) throws DocumentException, IOException {
        byte[] byteArray = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseFont fnt = BaseFont.createFont(webInfPath + "/resources/arial.ttf", "iso-8859-9", BaseFont.EMBEDDED);
        //FileOutputStream fileOutputStream = new FileOutputStream(webInfPath + "/HelloWorld2222.rtf");
        Document document = new Document();
        RtfWriter2 rtfWriter2 = RtfWriter2.getInstance(document, baos);
        document.open();

        Font fnPageHeader = new Font(fnt, 11, Font.BOLD);
        Font fnHeader = new Font(fnt, 10, Font.BOLD);
        Font fn = new Font(fnt, 10);

        float[] widths = {2f, 7f};
        float[] columns = {1f};

        RtfBorderGroup borderGroup = new RtfBorderGroup(Rectangle.BOX, RtfBorder.BORDER_SINGLE, 1, new Color(0, 0, 0));
        Paragraph tempParagraph = new Paragraph("RAPOR", fnPageHeader);
        tempParagraph.setAlignment(Chunk.ALIGN_CENTER);
        document.add(tempParagraph);
        document.add(Chunk.NEWLINE);

        Table table = new Table(2);
        table.setPadding(2f);
        table.setWidth(100.0f);
        table.setWidths(widths);


        table = prepareTable(columns, (float) 100.0, 9f);
        table.addCell(prepareNewCell("sdfsdfsdfZ", fnHeader));
        table.addCell(prepareNewCell("xdfdfsdfsdfdf", fn));
        table.addCell(prepareNewCell("dsfsdfsdfsdf", fnHeader));
        table.addCell(prepareNewCell("cellksdfjkdf", fn));

/**
 * Sample for adding checkbox to rtf file...
 */
        RtfDirectContent rdc = new RtfDirectContent("" +
                "{\\rtlch\\fcs1 \\af0 \\ltrch\\fcs0 \\insrsid9199969 {\\*\\bkmkstart Check1}}" +
                "{\\field\\fldpriv{\\*\\fldinst {\\rtlch\\fcs1 \\af0 \\ltrch\\fcs0 \\insrsid1590097 " +
                " FORMCHECKBOX }{\\rtlch\\fcs1 \\af0 \\ltrch\\fcs0 \\insrsid1590097 " +
                "{\\*\\datafield 650000001400000006436865636b3100000000000000000000000000}" +
                "{\\*\\formfield{\\fftype1\\ffres25\\fftypetxt0\\ffhps20{\\*\\ffname Check1}" +
                "\\ffdefres0}}}}{\\fldrslt }}\\sectd \\ltrsect\\linex0\\headery708\\footery708" +
                "\\colsx708\\endnhere\\sectlinegrid360\\sectdefaultcl\\sftnbj " +
                "{\\rtlch\\fcs1 \\af0 \\ltrch\\fcs0 \\insrsid9199969 {\\*\\bkmkend Check1}}");
        rdc.setInTable(true);
        RtfCell cell = new RtfCell(rdc);
        cell.setBorders(borderGroup);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        tempParagraph = new Paragraph("rtgrtertert", fn);
        document.add(Chunk.NEWLINE);
        tempParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(tempParagraph);



        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);


        document.close();
        byteArray = baos.toByteArray();
        baos.close();
        rtfWriter2.close();
        return byteArray;
    }

    private static Paragraph prepareNewParagraph(String value, Font fn) {
        if (value != null) {
            value = value.replaceAll("\r", "");
        }
        Paragraph p = new Paragraph(value, fn);
        return p;
    }

    private static Paragraph prepareNewParagraph(String value, Font fn, int horizontalAlignment) {
        Paragraph p = prepareNewParagraph(value, fn);
        p.setAlignment(horizontalAlignment);
        return p;
    }

    private static RtfCell prepareNewCell(String value, Font fn) throws BadElementException {
        RtfCell cell = new RtfCell();
        Paragraph p = prepareNewParagraph(value, fn, Element.ALIGN_LEFT);
        cell.addElement(p);
        return cell;
    }

    private static RtfCell prepareNewCell(String value, Font fn, RtfBorderGroup borderGroup) throws BadElementException {
        RtfCell cell = new RtfCell();
        Paragraph p = prepareNewParagraph(value, fn, Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setBorders(borderGroup);
        return cell;
    }

    private static RtfCell prepareNewCell(String value, Font fn, int horizontalAlignment) throws BadElementException {
        RtfCell cell = new RtfCell();
        Paragraph p = prepareNewParagraph(value, fn, horizontalAlignment);
        cell.addElement(p);
        return cell;
    }

    public static RtfCell prepareNewCell(String value, Font fn, int colspan, int borderWidth) throws BadElementException {
        RtfCell cell = prepareNewCell(value, fn);
        cell.setColspan(colspan);
        cell.setBorderWidth(borderWidth);
        return cell;
    }

    public static RtfCell prepareNewCell(String value, Font fn, int horizontalAlignment, int verticalAlignment, int colspan, int borderWidth) throws BadElementException {
        RtfCell cell = prepareNewCell(value, fn, horizontalAlignment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setColspan(colspan);
        cell.setBorderWidth(borderWidth);
        return cell;
    }

    public static RtfCell prepareNewCell(String value, Font fn, int horizontalAlignment, int verticalAlignment, int colspan, int borderWidth, RtfBorderGroup borderGroup) throws BadElementException {
        RtfCell cell = prepareNewCell(value, fn, horizontalAlignment, verticalAlignment, colspan, borderWidth);
        cell.setBorders(borderGroup);

        return cell;
    }

    private static Table prepareTable(float[] columns, float widthPercentage, float spaceBefore) throws BadElementException {
        Table table = new Table(columns.length);
        table.setWidth(widthPercentage);
        table.setSpacing(spaceBefore);
        return table;
    }
}