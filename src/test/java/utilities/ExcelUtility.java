package utilities;



import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheetName);
        XSSFRow row = ws.getRow(rownum);
        int cellCount = (row == null) ? 0 : row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet ws = wb.getSheet(sheetName);
        XSSFRow row = ws.getRow(rownum);
        XSSFCell cell = (row == null) ? null : row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data = "";
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }
}