package com.magneticreason.fitnium.writer;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelSpreadsheet implements FitniumWriter {

	private HSSFWorkbook wb     = null;
	private HSSFSheet sheet 	= null;
	private String name;
	
	public void initialise ( String params ) throws Exception {

		String[] data = params.split(",");
		if ( data.length != 2 ) 
			throw new Exception ( "Invalid parameters : " + params );

		this.name = data[0];
		
		// create a new workbook
		this.wb = new HSSFWorkbook();
		// create a new sheet
		this.sheet = this.wb.createSheet(data[1]);

		if ( null == this.sheet )
			throw new Exception ( "Faild to create worksheet : " + data[1] );
	}

	public void saveValue ( String value ) throws Exception {
		throw new Exception ( "Operation not support, use saveValue(value,params)");
	}
	
	public void saveValue ( String value, String params ) throws Exception {

		String[] coords = params.split(",");
		if ( coords.length != 2 ) 
			throw new Exception ( "Invalid parameters : " + params );
		
		int row = Integer.parseInt( coords[0] );
		int cell = Integer.parseInt( coords[1] );
		
		HSSFRow r = this.sheet.getRow( row );
		if ( null == r ) 
			r = this.sheet.createRow( row );

		HSSFCell c = r.getCell( cell );
		if ( null == c )
			c = r.createCell( cell );
		
		HSSFRichTextString rtString = new HSSFRichTextString ( value );
		c.setCellValue(rtString);
	}
	
	public void flush () throws Exception {
		// create a new file
		FileOutputStream out = new FileOutputStream(this.name);
		this.wb.write( out );
		out.close ();
	}

}
