// 
// Decompiled by Procyon v0.5.36
// 

package util.SendEmail;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import java.io.InputStream;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtils
{
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    
    public static void setExcelFile(final String FilePath) throws Exception {
        try {
            final FileInputStream ExcelFile = new FileInputStream(FilePath);
            ExcelUtils.ExcelWBook = new XSSFWorkbook((InputStream)ExcelFile);
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public static int getLastRownNo(final String SheetName) {
        ExcelUtils.ExcelWSheet = ExcelUtils.ExcelWBook.getSheet(SheetName);
        return ExcelUtils.ExcelWSheet.getLastRowNum();
    }
    
    public static String getCellData(final String SheetName, final int RowNum, final int ColNum) throws Exception {
        try {
            ExcelUtils.ExcelWSheet = ExcelUtils.ExcelWBook.getSheet(SheetName);
            final String CellData = ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue().toString();
            return CellData;
        }
        catch (Exception e) {
            try {
                if (ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == 1) {
                    return ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
                }
                if (ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == 0) {
                    final int value = (int)ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
                    return new StringBuilder(String.valueOf(value)).toString();
                }
                if (ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == 4) {
                    return new StringBuilder(String.valueOf(ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getBooleanCellValue())).toString();
                }
                if (ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == 3) {
                    return ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
                }
                if (ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == 5) {
                    return new StringBuilder(String.valueOf(ExcelUtils.ExcelWSheet.getRow(RowNum).getCell(ColNum).getErrorCellValue())).toString();
                }
                return "";
            }
            catch (Exception e2) {
                return "";
            }
        }
    }
    
    public static void setCellData(final String SheetName, final String Result, final int RowNum, final int ColNum, final short align) throws Exception {
        try {
            ExcelUtils.ExcelWSheet = ExcelUtils.ExcelWBook.getSheet(SheetName);
            ExcelUtils.Row = ExcelUtils.ExcelWSheet.getRow(RowNum);
            if (ExcelUtils.Cell == null) {
                (ExcelUtils.Cell = ExcelUtils.Row.createCell(ColNum)).setCellValue(Result);
            }
            else {
                (ExcelUtils.Cell = ExcelUtils.Row.createCell(ColNum)).setCellValue(Result);
            }
            final XSSFCellStyle style = ExcelUtils.ExcelWBook.createCellStyle();
            final XSSFFont font = ExcelUtils.ExcelWBook.createFont();
            style.setAlignment(align);
            font.setBold(true);
            style.setFont((Font)font);
            style.setBorderBottom((short)1);
            style.setBorderTop((short)1);
            style.setBorderRight((short)1);
            style.setBorderLeft((short)1);
            ExcelUtils.Cell.setCellStyle((CellStyle)style);
            final FileOutputStream fileOut = new FileOutputStream("./TestData/TestData.xlsx");
            ExcelUtils.ExcelWBook.write((OutputStream)fileOut);
            fileOut.flush();
            fileOut.close();
        }
        catch (Exception e) {
            final FileOutputStream fileOut2 = new FileOutputStream("./TestData/TestData.xlsx");
            ExcelUtils.ExcelWBook.write((OutputStream)fileOut2);
            fileOut2.flush();
            fileOut2.close();
            System.out.println("running set cell data catch block");
        }
    }
    
    public static void setCellData(final String SheetName, final String Result, final int RowNum, final int ColNum, final short color, final short align) throws Exception {
        try {
            ExcelUtils.ExcelWSheet = ExcelUtils.ExcelWBook.getSheet(SheetName);
            ExcelUtils.Row = ExcelUtils.ExcelWSheet.getRow(RowNum);
            ExcelUtils.Cell = ExcelUtils.Row.getCell(ColNum);
            if (ExcelUtils.Cell == null) {
                (ExcelUtils.Cell = ExcelUtils.Row.createCell(ColNum)).setCellValue(Result);
            }
            else {
                ExcelUtils.Cell.setCellValue(Result);
            }
            final XSSFCellStyle style = ExcelUtils.ExcelWBook.createCellStyle();
            final XSSFFont font = ExcelUtils.ExcelWBook.createFont();
            style.setAlignment(align);
            font.setBold(true);
            font.setColor(color);
            style.setFont((Font)font);
            style.setBorderBottom((short)1);
            style.setBorderTop((short)1);
            style.setBorderRight((short)1);
            style.setBorderLeft((short)1);
            ExcelUtils.Cell.setCellStyle((CellStyle)style);
            final FileOutputStream fileOut = new FileOutputStream("./TestData/TestData.xlsx");
            ExcelUtils.ExcelWBook.write((OutputStream)fileOut);
            fileOut.flush();
            fileOut.close();
        }
        catch (Exception e) {
            final FileOutputStream fileOut2 = new FileOutputStream("./TestData/TestData.xlsx");
            ExcelUtils.ExcelWBook.write((OutputStream)fileOut2);
            fileOut2.flush();
            fileOut2.close();
            System.out.println("running set cell data catch block");
        }
    }
}
