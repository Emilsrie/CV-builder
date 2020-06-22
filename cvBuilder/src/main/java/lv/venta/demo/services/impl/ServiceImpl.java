package lv.venta.demo.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.CV;
import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;
import lv.venta.demo.repo.ICVRepo;
import lv.venta.demo.repo.IEducationRepo;
import lv.venta.demo.repo.IJobExperienceRepo;
import lv.venta.demo.repo.ILanguagesRepo;
import lv.venta.demo.services.IService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;




@Service
public class ServiceImpl implements IService{


	
	
	@Autowired
	ICVRepo cvRepo;
	
	@Autowired
	IEducationRepo educationRepo;
	
	@Autowired
	IJobExperienceRepo jobExperienceRepo;
	
	@Autowired
	ILanguagesRepo languagesRepo;
	
	
	@Override
	public ArrayList<JobExperience> selectAllJobExperiences(){
		return (ArrayList<JobExperience>) jobExperienceRepo.findAll();
	}
	
	@Override
	public ArrayList<Education> selectAllEducations(){
		return (ArrayList<Education>) educationRepo.findAll();
	}
	
	@Override
	public ArrayList<Languages> selectAllLanguages(){
		return (ArrayList<Languages>) languagesRepo.findAll();
	}

	@Override
	public ArrayList<CV> selectAllCVs()
	{
		return (ArrayList<CV>) cvRepo.findAll();
 	}

	@Override
	public void insertCV(CV cv)
	{
		cvRepo.save(cv);
		
	}
	
	@Override 
	public void insertEducation(Education edu) {
		educationRepo.save(edu);
	}

	@Override
	public void insertJobExperience(JobExperience jExp) {
		jobExperienceRepo.save(jExp);
		
	}

	@Override
	public void insertLanguage(Languages lang) {
		languagesRepo.save(lang);
		
	}


	/*
	@Override
	public void putAllDataInFile() {
		try {
		File myfile = new File("testfile.txt");
		
			if(myfile.createNewFile())
			{
				System.out.println("File created: " + myfile.getName());
			}
			else
			{
				System.out.println("already exists");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			FileWriter myWriter = new FileWriter("testfile.txt");
			for(CV c: cvRepo.findAll())
			{
				myWriter.write(c.toString());
			}
			for(JobExperience j: jobExperienceRepo.findAll())
			{
				myWriter.write(j.toString());
			}
			for(Education e: educationRepo.findAll())
			{
				myWriter.write(e.toString());
			}
			for(Languages l: languagesRepo.findAll())
			{
				myWriter.write(l.toString());
			}
			myWriter.close();
		}catch (IOException e)
		{
			System.out.println("error");
			e.printStackTrace();
		}
	}
	*/

	@Override
	public void createPDF() throws IOException {
		
		
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("MyCV.pdf"));
			document.open();
			
			document.add(new Paragraph("It works"));
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f, 2f, 2f};
			table.setWidths(colWidth);
			PdfPCell c1 = new PdfPCell(new Paragraph("Column1"));
			PdfPCell c2 = new PdfPCell(new Paragraph("Column1"));
			PdfPCell c3 = new PdfPCell(new Paragraph("Column1"));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			document.add(table);
			
			List orderList = new List(List.ORDERED);
			orderList.add(new ListItem("Fun"));
			orderList.add(new ListItem("That"));
			orderList.add(new ListItem("Ends"));
			document.add(orderList);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("That"));
			unorderList.add(new ListItem("is"));
			unorderList.add(new ListItem("nice"));
			document.add(unorderList);
			
			
			document.close();
			writer.close();
	
		}catch(DocumentException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	
}
