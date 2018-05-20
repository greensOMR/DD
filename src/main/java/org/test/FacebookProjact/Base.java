package org.test.FacebookProjact;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Base {
	public static void main(String[] args) throws Throwable {
		String data = getData(2, "id");
		System.out.println(data);
	}
	public static String getData(int rowNum, String columnName) throws Throwable {
		List<LinkedHashMap<String, String>> mapDatasList = new ArrayList<LinkedHashMap<String, String>>();
		File excelLocaltion = new File(
				"C:\\Users\\DELL\\eclipse-workspace\\HexawareBatch1567\\FacebookProjact\\TestData\\Result.xlsx");
		String sheetName = "Datas";
		FileInputStream f = new FileInputStream(excelLocaltion);
		Workbook w = new XSSFWorkbook(f);
		Sheet sheet = w.getSheet(sheetName);
		Row headerRow = sheet.getRow(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row currentRow = sheet.getRow(i);
			LinkedHashMap<String, String> mapDatas = new LinkedHashMap<String, String>();

			for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
				Cell currentCell = currentRow.getCell(j);
				int type = currentCell.getCellType();
				if (type == 1) {
					String colum = headerRow.getCell(j).getStringCellValue();
					mapDatas.put(colum, currentCell.getStringCellValue());
				} else if (type == 0) {
					double d = currentCell.getNumericCellValue();
					long l = (long) d;
					mapDatas.put(headerRow.getCell(j).getStringCellValue(), String.valueOf(l));
				}
			}

			mapDatasList.add(mapDatas);
		}
		return mapDatasList.get(rowNum).get(columnName);

	}

}

