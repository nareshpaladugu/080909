package com.nareshcompany.datadriven;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestData {
	
	public static String getCellData(String colunName,String fileName) throws BiffException, IOException
	{
		String userDir = System.getProperty("user.dir");
		Workbook wb= Workbook.getWorkbook(new File(userDir+"\\src\\main\\java\\in\\redbus\\testdata\\"+fileName));
		Sheet sh = wb.getSheet(0);
		int count = 0;
		for(int i=0;i<sh.getColumns();i++)
		{
			if(sh.getCell(i, 0).getContents().equals(colunName))
			{
				break;
			}
			count++;
		}
		
		return sh.getCell(count, 1).getContents();	
}
}
