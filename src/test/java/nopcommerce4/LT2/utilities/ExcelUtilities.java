package nopcommerce4.LT2.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	private static XSSFWorkbook excelBook;
	private static XSSFSheet excelSheet;

	public static void setExcelFile(String path, String sheetName) {

		try {
			FileInputStream fileInput = new FileInputStream(path);
			excelBook = new XSSFWorkbook(fileInput);
			excelSheet = excelBook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public static XSSFCell[] findCells(String verifyName) {
		DataFormatter formatter = new DataFormatter();
		XSSFCell[] cells = new XSSFCell[2];

		String pos = "begin";

		for (Row row : excelSheet) {
			for (Cell cell : row) {
				if (verifyName.equals(formatter.formatCellValue(cell))) {
					if (pos == "begin") {
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
					}
				}
			}

		}
		return cells;
	}

	public static String[][] getData(String verifyName) {
		String[][] userData = null;

		try {
			DataFormatter formatData = new DataFormatter();

			XSSFCell[] boundryCell = findCells(verifyName);
			XSSFCell beginCell = boundryCell[0];
			XSSFCell endCell = boundryCell[1];
			
			int startRow = beginCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = beginCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			userData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {

					XSSFCell cell = excelSheet.getRow(i).getCell(j);
					userData[i-startRow][j-startCol] = formatData.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		return userData;
	}

}
