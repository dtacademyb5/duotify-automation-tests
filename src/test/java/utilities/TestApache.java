package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TestApache {

    public static void main(String[] args) throws IOException {
//
//        FileInputStream fis = new FileInputStream("TestDataExcel.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet("Sheet1");
//        XSSFRow headerRow  =  sheet.getRow(0);
//        XSSFCell cell = headerRow.getCell(0);
//
////        int noOfColumns = headerRow.getPhysicalNumberOfCells();
//        int noOfColumns = headerRow.getLastCellNum();
//        int noOfRows = sheet.getPhysicalNumberOfRows();
//
//        System.out.println(noOfColumns);
//        System.out.println(noOfRows);
//
//
//        for (int i = 0; i < noOfRows; i++) {
//
//            for (int j = 0; j < noOfColumns; j++) {
//                System.out.print(sheet.getRow(i).getCell(j) + " ");
//            }
//
//            System.out.println();
//
//        }


//        FileOutputStream fos = new FileOutputStream("TestDataExcel.xlsx");
//
//
//        sheet.getRow(0).getCell(1).setCellValue("PASS");
//
//        workbook.write(fos);



        ExcelUtility excelUtility = new ExcelUtility("testDataExcel.xlsx", "sheet1");

        System.out.println(excelUtility.getCellData(0, 2));

        System.out.println(excelUtility.getColumnNames());


        System.out.println(Arrays.deepToString(excelUtility.getDataAs2DArray()));


      
















    }
}
