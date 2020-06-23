package lv.venta.demo.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
		if(!Files.isDirectory(Paths.get("C:\\PDF")))
		{
			System.out.println("didnt find");
			new File("C:\\PDF").mkdirs();
		}
		
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF\\MyCV.pdf"));
			document.open();
			
			/*
			PdfContentByte canvas = writer.getDirectContent();
			canvas.rectangle(22, 774, 550, 20);
			canvas.setColorFill(BaseColor.LIGHT_GRAY);
		    canvas.fill();
			*/
			
			Font nameFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Paragraph nameAndSurname = new Paragraph("Richard Watterson", nameFont);
			nameAndSurname.setAlignment(Element.ALIGN_CENTER);
			document.add(nameAndSurname);
			document.add(Chunk.NEWLINE);
			
			Font phoneNrFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Paragraph phoneNumber = new Paragraph("Phone number: " + "22244491", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(phoneNumber);
			
			Paragraph email = new Paragraph("Email: " + "Watterson@gmail.com", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(email);
			
			Paragraph address = new Paragraph("Address: " + "Wallstreet 29", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(address);
			
			
			Paragraph city = new Paragraph("City: " + "Washington", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(city);
			
			Paragraph province = new Paragraph("Province: " + "ProvinceNonde", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(province);
			
			Paragraph zipCode = new Paragraph("ZipCode: " + "UK3312", phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(zipCode);
			
			
			
			
			
			
			
			
			PdfPTable tableBg = new PdfPTable(1);
			tableBg.setWidthPercentage(105);
			tableBg.setSpacingBefore(25f);
			tableBg.setSpacingAfter(5f);
			
			
			
			float[] colWidth = {2f};
			tableBg.setWidths(colWidth);
			Font bgFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Paragraph backgroundInfo = new Paragraph("Background information", bgFont);
			PdfPCell c1 = new PdfPCell(backgroundInfo);
			tableBg.addCell(c1);
			document.add(tableBg);
			Font bgTextFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			Paragraph backgroundInfoText = new Paragraph("texttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext.",
					bgTextFont);
			document.add(backgroundInfoText);
			
			PdfPTable tableOs = new PdfPTable(1);
			tableOs.setWidthPercentage(105);
			tableOs.setSpacingBefore(25f);
			tableOs.setSpacingAfter(5f);
		
			tableOs.setWidths(colWidth);
			Paragraph otherSkills = new Paragraph("Other skills", bgFont);
			PdfPCell c2 = new PdfPCell(otherSkills);
			tableOs.addCell(c2);
			document.add(tableOs);
			Paragraph osInfoText = new Paragraph("texttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext.",
					bgTextFont);
			document.add(osInfoText);
			
			
			PdfPTable tableEdu = new PdfPTable(1);
			tableEdu.setWidthPercentage(105);
			tableEdu.setSpacingBefore(25f);
			tableEdu.setSpacingAfter(5f);
		
			tableEdu.setWidths(colWidth);
			Paragraph education = new Paragraph("Education", bgFont);
			PdfPCell c3 = new PdfPCell(education);
			tableEdu.addCell(c3);
			document.add(tableEdu);
			Paragraph educationText = new Paragraph("texttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext.",
					bgTextFont);
			document.add(educationText);
			
			PdfPTable tableJobExp = new PdfPTable(1);
			tableJobExp.setWidthPercentage(105);
			tableJobExp.setSpacingBefore(25f);
			tableJobExp.setSpacingAfter(5f);
		
			tableJobExp.setWidths(colWidth);
			Paragraph jobExp = new Paragraph("Job Experience", bgFont);
			PdfPCell c4 = new PdfPCell(jobExp);
			tableJobExp.addCell(c4);
			document.add(tableJobExp);
			Paragraph jobExpText = new Paragraph("texttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext.",
					bgTextFont);
			document.add(jobExpText);
			
			PdfPTable tableLang = new PdfPTable(1);
			tableLang.setWidthPercentage(105);
			tableLang.setSpacingBefore(25f);
			tableLang.setSpacingAfter(5f);
		
			tableLang.setWidths(colWidth);
			Paragraph lang = new Paragraph("Languages", bgFont);
			PdfPCell c5 = new PdfPCell(lang);
			tableLang.addCell(c4);
			document.add(tableLang);
			Paragraph languagesText = new Paragraph("texttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
					+ "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext.",
					bgTextFont);
			document.add(languagesText);
			
			
		
			
			/*
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
			*/
			
			document.close();
			writer.close();
	
		}catch(DocumentException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
