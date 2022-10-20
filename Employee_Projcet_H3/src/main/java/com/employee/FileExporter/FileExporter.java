package com.employee.FileExporter;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


import com.employee.entity.Employee;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class FileExporter {
	
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
	
	
	public void exportToPdf(List<Employee> empList,HttpServletResponse response) throws DocumentException, IOException
	{
		setResponceHeader(response, "application/pdf", ".pdf", "Employee_");
		Document document=new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		//font.setColor(Color.BLACK);
		
		Paragraph para=new Paragraph("Employee List",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);
		
		
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10);
		
		
		
		writeEmployeeHeader(table);
		writeEmployeeData(table, empList);
		
		document.add(table);
		document.close();
		
	}
	
	private void writeEmployeeHeader(PdfPTable table)
	{
		PdfPCell cell=new PdfPCell();
		//cell.setBackgroundColor(Color.orange);
		cell.setPadding(5);
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		cell.setPhrase(new Phrase("Employee ID", font));
        
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("City", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Salary", font));
        table.addCell(cell);
         
        
	}
	
	private void writeEmployeeData(PdfPTable table,List<Employee> empList)
	{
		for(Employee emp:empList)
		{
			table.addCell(String.valueOf(emp.getId()));
            table.addCell(emp.getName());
            table.addCell(emp.getCity());
            table.addCell(emp.getSalary().toString());
		}
		
	}
	
	
	

}
