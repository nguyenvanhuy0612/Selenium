package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {
	public static String[][] get(String filename) {
		String[][] dataTable = null;
		File file = new File(filename);
		try {
			FileInputStream xlfile = new FileInputStream(file);
			HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
			HSSFSheet xlSheet = xlwb.getSheetAt(0);
			
			int numRows = xlSheet.getLastRowNum() + 1;
			int numCols = xlSheet.getRow(0).getLastCellNum();
			
			dataTable = new String[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				HSSFRow xlRow = xlSheet.getRow(i);
				for (int j = 0; j < numCols; j++) {
					HSSFCell xlCell = xlRow.getCell(j);
					dataTable[i][j] = xlCell.toString();
				}
			}
		}
		catch (IOException e) {
			System.out.println("ERROR FILE HANDLING "+e.toString());
		}
		return dataTable;
	}
}
