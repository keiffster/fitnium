package com.magneticreason.fitnium.generator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelSpreadsheet implements FitniumUniqueValueGenerator {

	private HSSFWorkbook wb     = null;
	private HSSFSheet sheet 	= null;
	
	public void initialise ( String params ) throws Exception {
		
		String[] data = params.split(",");
		if ( data.length != 2 ) 
			throw new Exception ( "Invalid parameters : " + params );
		
		InputStream myxls = new FileInputStream( data[0] );
		this.wb     = new HSSFWorkbook(myxls);
		this.sheet = this.wb.getSheet( data[1] );
	}

	public String getNextValue()  throws Exception {
		throw new Exception ( "Method not supported" );
	}

	public String getNextValue ( String params )  throws Exception {
		if ( null == this.wb || this.sheet == null ) 
                    throw new Exception ( "Workbook not initialised" );
		
		String[] coords = params.split(",");
		if ( coords.length != 2 ) 
                    throw new Exception ( "Invalid parameters : " + params );
		
		int row = Integer.parseInt( coords[0] );
		int col = Integer.parseInt( coords[1] );
		
		return this.getCellStringValue( this.sheet, row, col );
	}

	public void reset(String intialValue)  throws Exception {
	}

	public void reset()  throws Exception {
	}
	
	private String getCellStringValue ( HSSFSheet sheet, int rowNo, int colNo ) throws Exception {
		
		HSSFRow row     = sheet.getRow(rowNo);        
		if ( null == row ) {
                    System.err.println ( "Invalid row number : " + rowNo );
                    return "";
		}
		
		HSSFCell cell   = row.getCell(colNo);
		if ( null == cell ) {
                    System.err.println  ( "Invalid column number : " + colNo );
                    return "";
		}

		switch ( cell.getCellType() ) {
		case HSSFCell.CELL_TYPE_BOOLEAN :
                    return Boolean.toString( cell.getBooleanCellValue() );
		case HSSFCell.CELL_TYPE_NUMERIC :
                    double val=cell.getNumericCellValue();
                    NumberFormat nm = NumberFormat.getNumberInstance();
                    nm.setGroupingUsed ( false );
		    return nm.format(val);
		case HSSFCell.CELL_TYPE_STRING :
                    return cell.getRichStringCellValue().getString();
		case HSSFCell.CELL_TYPE_BLANK :
		default :
                    return "";
		}		
	}
	
}