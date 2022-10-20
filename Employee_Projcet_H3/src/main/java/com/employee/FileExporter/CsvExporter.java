package com.employee.FileExporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.AbstractCsvWriter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.employee.entity.Employee;
import com.itextpdf.text.DocumentException;

@Component
public class CsvExporter {
	
	public void setResponceHeader(HttpServletResponse response, String contentType,String extension,String prefix)
	{
		DateFormat  dateFormat=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp= dateFormat.format(new Date());
		String fileName=prefix+timeStamp+extension;
		
		response.setContentType(contentType);
		
		String headerKey="contemt-Desposition";
		String headerValue="attachement:filename:-"+fileName;
		response.setHeader(headerKey, headerValue);
		
	}
	
	
	public void exportToCsv(List<Employee> empList,HttpServletResponse response) throws DocumentException, IOException
	{
		setResponceHeader(response, "text/csv", ".csv", "Employee_");
		CsvBeanWriter csvWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Employee Id","Employee Name","City","Salary"};
		String[]  fieldMapping= {"id","name","city","salary"};
		
		
		csvWriter.writeHeader(csvHeader);
		
		
		for(Employee emp:empList)
		{
			csvWriter.write(emp, fieldMapping);
		}
	
	}

}
