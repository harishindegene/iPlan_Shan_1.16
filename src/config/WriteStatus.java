// 
// Decompiled by Procyon v0.5.36
// 

package config;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import dataProvider.ExcelDataProvider;

public class WriteStatus extends ExcelDataProvider
{
    public static void writestatusof() throws IOException {
        final FileInputStream fis = new FileInputStream("./TestCases/Testcases.xlsx");
        final XSSFWorkbook workbook = new XSSFWorkbook((InputStream)fis);
        final XSSFSheet sheet = workbook.getSheet("Testcases");
        final Row row = (Row)sheet.getRow(2);
        final Cell cell = row.createCell(4);
        cell.setCellType(1);
        cell.setCellValue("PASS");
        final FileOutputStream fos = new FileOutputStream("./TestCases/Testcases.xlsx");
        workbook.write((OutputStream)fos);
        fos.close();
        System.out.println("END OF WRITING DATA IN EXCEL");
    }
}
