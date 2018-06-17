package com.springmvc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.springmvc.model.DataModel;


@Service("excelService")
public class ExcelServiceImpl{




	public HashMap<String,Double> getExcelData(String year,String month,String day) throws IOException {
		HashMap<String,Double> data;
		data= populateData(year,month,day);
		return data;
	}


	private HashMap<String,Double> populateData(String year,String month,String day) throws IOException{
		List<DataModel> dataList=new ArrayList<DataModel>();
		
		URL url = getClass().getResource("/Book1.xlsx");
		File excelFile = new File(new StringBuilder(url.getPath()).toString());
		Workbook workbook=null;
		HashMap<String,Double> averageData=new HashMap<String,Double>();		
		InputStream objectData = null;
		objectData = new FileInputStream(excelFile);
		DateFormat dateFormat=null;
		
		
		if(null==year) {
			year="";
		}
		if(null==month) {
			month="";
		}
		if(null==day) {
			day="";
		}
		workbook = new XSSFWorkbook(objectData);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			java.util.Iterator<Row> rowIterator = sheet.iterator();
			Row row=rowIterator.next();
			while(rowIterator.hasNext()) {
				row=rowIterator.next();
				DataModel dataModel=new DataModel();
				if(row.getCell(0).getCellType() != row.getCell(0).CELL_TYPE_BLANK) {
				dataModel.setDate(row.getCell(0).getDateCellValue());
				dataModel.setOpen(row.getCell(1).toString());
				dataModel.setHigh(row.getCell(2).toString());
				dataModel.setLow(row.getCell(3).toString());
				dataModel.setAdjClose(row.getCell(4).toString());
				dataModel.setVolume(row.getCell(5).toString());
				dataList.add(dataModel);
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");				
				
				if(year.equalsIgnoreCase("") && month.equalsIgnoreCase("") && day.equalsIgnoreCase("")) {					
					averageData=populateAllData(averageData,year,month,day,dateFormat,dataModel);					
				}
				
				else if(month.equalsIgnoreCase("") && day.equalsIgnoreCase("")) {
					averageData=populateYearData(averageData,year,month,day,dateFormat,dataModel);
				
				}
				else if(!year.isEmpty() && !month.isEmpty() && day.isEmpty()) {
					averageData=populateMonthData(averageData,year,month,day,dateFormat,dataModel);
					}
				
				else if(!year.isEmpty() && !month.isEmpty() && !day.isEmpty()) {
					
					averageData=populateDayData(averageData,year,month,day,dateFormat,dataModel);
					}
				}
				
				
			}
		}
		return averageData;
	}
	
	private HashMap<String,Double> populateDayData(HashMap<String,Double> averageData,String year,String month,String day,DateFormat dateFormat,DataModel dataModel) throws IOException{
		 String queryParam=year+"-"+month+"-"+day;
		 String excelDate=null;
		 excelDate=dateFormat.format(dataModel.getDate()).toString().substring(0, dateFormat.format(dataModel.getDate()).toString().length());
		
			if(excelDate.equals(queryParam)){
				dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				if(dataModel.getDate().equals(averageData.containsKey(dataModel.getDate()))){
				Double AdjClose=((averageData.get(dataModel.getDate())+(Double.valueOf(dataModel.getAdjClose())))/2);
				averageData.put(dateFormat.format(dataModel.getDate()).toString(),AdjClose);
			    }
			    else
					{					
					    
					  	averageData.put( dateFormat.format(dataModel.getDate()).toString(), Double.valueOf(dataModel.getAdjClose()));
					}
			}
			return averageData;
	}
		
	private HashMap<String,Double> populateYearData(HashMap<String,Double> averageData,String year,String month,String day,DateFormat dateFormat,DataModel dataModel) throws IOException{
		 dateFormat = new SimpleDateFormat("yyyy");
		
		if(year.equalsIgnoreCase(dateFormat.format(dataModel.getDate()).toString())) {
			 DateFormat dateFormat1 = new SimpleDateFormat("MMM-yyyy");
			if(dataModel.getDate().equals(averageData.containsKey(dataModel.getDate()))){
				Double AdjClose=((averageData.get(dataModel.getDate())+(Double.valueOf(dataModel.getAdjClose())))/2);
				averageData.put(dateFormat.format(dataModel.getDate()).toString(),AdjClose);
			}
			else
			{
				
				averageData.put( dateFormat1.format(dataModel.getDate()).toString(), Double.valueOf(dataModel.getAdjClose()));
			}
		}
		return averageData;
	}
	
	private HashMap<String,Double> populateMonthData(HashMap<String,Double> averageData,String year,String month,String day,DateFormat dateFormat,DataModel dataModel) throws IOException{
			 String queryParam=year+"-"+month;
			 String excelDate=null;
			 excelDate=dateFormat.format(dataModel.getDate()).toString().substring(0, dateFormat.format(dataModel.getDate()).toString().length()-3);
			
				if(excelDate.equals(queryParam)){
					dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					if(dataModel.getDate().equals(averageData.containsKey(dataModel.getDate()))){
					Double AdjClose=((averageData.get(dataModel.getDate())+(Double.valueOf(dataModel.getAdjClose())))/2);
					averageData.put(dateFormat.format(dataModel.getDate()).toString(),AdjClose);
				    }
				    else
						{					
						    
						  	averageData.put( dateFormat.format(dataModel.getDate()).toString(), Double.valueOf(dataModel.getAdjClose()));
						}
				}
				return averageData;
		}
	
	
	@SuppressWarnings("unlikely-arg-type")
	private HashMap<String,Double> populateAllData(HashMap<String,Double> averageData,String year,String month,String day,DateFormat dateFormat,DataModel dataModel) throws IOException{
		 dateFormat = new SimpleDateFormat("yyyy");
		
			if(dataModel.getDate().equals(averageData.containsKey(dataModel.getDate()))){
				Double AdjClose=((averageData.get(dataModel.getDate())+(Double.valueOf(dataModel.getAdjClose())))/2);
				averageData.put(dateFormat.format(dataModel.getDate()).toString(),AdjClose);
			}
			else
			{
				 averageData.put( dateFormat.format(dataModel.getDate()).toString(), Double.valueOf(dataModel.getAdjClose()));
			}
			return averageData;
	   }
	}
	
	
