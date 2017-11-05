/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {

    public static byte[] xlsGenerator(List arbisUserQueryResultList) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        HSSFRow rowHeader = sheet.createRow((short) 0);

        rowHeader.setHeightInPoints(20f);
        String headerText = "SORGU SONUÇLARI";
        createCellWithBoldFont(wb, rowHeader, 0, headerText, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER);
        rowHeader = sheet.createRow((short) 1);
        rowHeader.setHeightInPoints(20f);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 24));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 15));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 24));

        List header = new ArrayList();
        header.add("No");                       //0               
        header.add("Kişi No");            //1
        header.add("Kişi");                     //2

        setHeader(wb, sheet, header, 2);

        sheet.setDefaultColumnWidth(40);
        sheet.setColumnWidth(0, 1024);
        sheet.setColumnWidth(1, 2560);
        sheet.setColumnWidth(2, 2560);

        int rowCount = 2;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        wb.write(baos);
        byte[] byteArray = baos.toByteArray();
        baos.close();

        return byteArray;
    }


    public static void setHeader(HSSFWorkbook wb, HSSFSheet sheet, List headerList, int headerRow) {

        int cellCount = 0;
        HSSFRow row = sheet.createRow((short) headerRow);
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setLocked(true);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
        row.setRowStyle(cellStyle);

        for (Iterator iter = headerList.iterator(); iter.hasNext();) {
            HSSFCell cell = row.createCell(cellCount);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString((String) iter.next()));

            cellCount++;
        }
    }

    private static void createCell(HSSFWorkbook wb, HSSFRow row, int column, String cellValue, short halign, short valign) {
        HSSFCell cell = row.createCell(column);
        cell.setCellValue(new HSSFRichTextString(cellValue));
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setWrapText(true);
        cell.setCellStyle(cellStyle);
    }

    private static void createCellWithBoldFont(HSSFWorkbook wb, HSSFRow row, int column, String cellValue, short halign, short valign) {

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCell cell = row.createCell(column);
        cell.setCellValue(new HSSFRichTextString(cellValue));
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setFont(font);
        cellStyle.setLocked(true);
        cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
        cell.setCellStyle(cellStyle);
        row.setRowStyle(cellStyle);

    }

    public byte[] drawPicture(String pictureUrl) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        HSSFRow row = sheet.createRow(1);
        HSSFCell cell = row.createCell(0);
        

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        wb.write(baos);
        byte[] byteArray = baos.toByteArray();
        baos.close();

        return byteArray;
    }
}
