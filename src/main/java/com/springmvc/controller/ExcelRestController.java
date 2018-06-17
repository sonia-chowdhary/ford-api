package com.springmvc.controller;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import com.springmvc.service.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExcelRestController {
 
    @Autowired
    ExcelServiceImpl excelService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Data--------------------------------------------------------@RequestParam("name") Optional<String> username,
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String,Double>> getExcelData(@RequestParam(required = false, name = "year") String year,
    		@RequestParam(required = false, name="month") String month,
    		@RequestParam(required = false, name="day") String day    		
    		)throws IOException {
   
    	HashMap<String,Double> data = excelService.getExcelData(year,month,day);
        if(data.isEmpty()){
        	System.out.println("Data--"+data);
        	data.put("Bad Request", 400.00);
        	 return new ResponseEntity<HashMap<String,Double>>(data, HttpStatus.BAD_REQUEST);
          //  return new ResponseEntity<HashMap<String,Double>>("Bad Request",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HashMap<String,Double>>(data, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Data--------------------------------------------------------
 
}