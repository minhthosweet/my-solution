package automation.PestRoutes.Utilities.Data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelData {
    //F.White - 12/11/2021 : Added method readExcelFile()
    //Method readExcelFile(): Reads files from an excel spreadsheet and places it in a HaspMap<String, String>
    public static HashMap<String, String> readExcelFile(String filePathLocation, String fileName, String sheetName, int dataSetID) throws IOException {
        HashMap<String, String> fileDataMap =  new  HashMap<String, String>();
        String fileFullPathLocation = filePathLocation + File.separator + fileName;
        FileInputStream fis= null;

        try {
            File file = new File(fileFullPathLocation);
            fis = new FileInputStream(file);
            XSSFWorkbook workbookName = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbookName.getSheet(sheetName);

            //Load Data into a HashMap
            Row rowHeader = sheet.getRow(0);
            List<String> columnHeader = new ArrayList<String>();

            //Load The Header Row
            Iterator<Cell> cellIterator = rowHeader.cellIterator();
            while (cellIterator.hasNext())
            {
                columnHeader.add(cellIterator.next().getStringCellValue());
            }

            //Load the HashMap
            int rowCount = rowHeader.getLastCellNum();
            int columnCount = rowHeader.getLastCellNum();

            Row row = sheet.getRow(dataSetID);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                fileDataMap.put(columnHeader.get(j), getCellValueAsString(cell));
            }

        }catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            if (fis != null)
            {
                fis.close();
            }
        }
        return fileDataMap;
    }//readExcelFile()

    //F.White - 12/11/2021: Added method readExcelFile(). It called by readExelFile()
    //Method getCellValueAsString(): Returns the cell value in a Hashmap as a string
    public static String getCellValueAsString(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
            case BLANK:
                cellValue = "BLANK";
            default:
                cellValue = "DEFAULT";
        }
        return cellValue;
    }//getCellValueAsString()
}
