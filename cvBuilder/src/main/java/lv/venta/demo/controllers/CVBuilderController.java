package lv.venta.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lv.venta.demo.models.CV;
import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;
import lv.venta.demo.services.impl.ServiceImpl;

//http://localhost:8080/h2-console
//jdbc:h2:file:~/cv
@Controller
@RequestMapping("/cvBuilder")
public class CVBuilderController
{
	@Autowired
	ServiceImpl serviceImpl; 
 
	
	//start page, all the basic personal info is input here such as Name Surname Phone Nr. etc.
	@GetMapping("/build") //localhost:8080/cvBuilder/build
	public String makeCVGet(CV cv) {
		return "data_input";
	}
	
	//once basic data is submitted if no errors occurred a CV object is saved in the repository,
	//and the user is directed further to the job section
	@PostMapping("/build")
	public String makeCVPost(@Valid CV cv, BindingResult result) {
		//For testing
		System.out.println(cv);
		
		if(!result.hasErrors()) {
			serviceImpl.insertCV(cv);
			return "redirect:/cvBuilder/job";
		} else {
			return "data_input";
		}
	}
	
	
	//shows html page for entering job experience, the page has 2 submit buttons one which sends the data to the same url and one which sends it to job/cont
	@GetMapping("/job") //localhost:8080/cvBuilder/job
	public String insertJobExperienceGet(JobExperience jobExperience) {
		return "job-input";
	}
	
	
	//once job data is submitted if no errors occurred a JobExperience object is saved in the repository,
	//and the user is directed back to this url, and can make another job experience entry
	@PostMapping("/job")
	public String insertJobExperiencePost(@Valid JobExperience jobExperience, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/job";
		} else {
			return "job-input";
		}
	}
	
	
	//once job data is submitted if no errors occurred a JobExperience object is saved in the repository,
	//and the user is directed to the education info section
	@PostMapping("/job/cont")
	public String insertJobAndContinuetoEducation(@Valid JobExperience jobExperience, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/edu";
		} else {
			return "job-input";
		}
	}
	 
	
	//shows html page for entering education information, the page has 2 submit buttons one which sends the data to the same url and one which sends it to edu/cont
	@GetMapping("/edu") //localhost:8080/cvBuilder/edu
	public String insertEducation(Education education) {
		return "edu-input";
	}
	
	
	//once education data is submitted if no errors occurred an Education object is saved in the repository,
	//and the user is directed back to this url, and can make another education entry
	@PostMapping("/edu")
	public String insertEducationNew(@Valid Education education, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/edu";
		} else {
			return "edu-input";
		}
	}
	
	
	//once job data is submitted if no errors occurred an Education object is saved in the repository,
	//and the user is directed to the languages section
	@PostMapping("/edu/cont")
	public String insertEducationAndContinueToLanguages(@Valid Education education, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/languages";
		} else {
			return "edu-input";
		}
	}
	
	
	//shows html page for entering languages information, which is entered using dropdowns of language proficiency levels, the page has 2 submit buttons one which sends the data to the same url and one which sends it to languages/cont
	@GetMapping("/languages") //localhost:8080/cvBuilder/languages
	public String insertLanguages(Languages languages) {
		return "languages-input";
	}
	
	
	//once education data is submitted if no errors occurred a Languages object is saved in the repository,
	//and the user is directed back to this url, and can make another languages entry
	@PostMapping("/languages")
	public String insertLanguagesNew(@Valid Languages languages, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/languages";
		} else {
			return "languages-input";
		}
	}
	
	//once job data is submitted if no errors occurred a Languages object is saved in the repository,
	//and the user is directed to the final page
	@PostMapping("/languages/cont")
	public String insertLanguagesAndContinueToCreation(@Valid Languages languages, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/done";
		} else {
			return "languages-input";
		}
	}
	

	//once all of the data has been entered, the user reaches this page, the CV has been created in PDF form, the repositories are cleaned and the user can press a button to download the PDF from the server
	@GetMapping("/done")
	public String sayCVIsDone() throws IOException {
		serviceImpl.createPDF();
		serviceImpl.clearEducation();
		serviceImpl.clearJobs();
		serviceImpl.clearLanguages();
		serviceImpl.clearCV();
		return "download";
	}
	

	//if download button is pressed in the download html page, we check if the MyCv.pdf file exists in the directory where it was created and send it to the user as a download
	@RequestMapping("/download") ////localhost:8080/cvBuilder/download
	public void givePDF(HttpServletResponse response) {
		String dataDirectory = "C:\\PDF";
		Path file = Paths.get(dataDirectory, "MyCv.pdf");
		if (Files.exists(file)) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename ="+"MyCv.pdf");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
