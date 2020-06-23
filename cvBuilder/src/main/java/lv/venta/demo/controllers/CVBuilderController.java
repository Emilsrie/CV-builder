package lv.venta.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/cvBuilder") //localhost:8080/cvBuilder
public class CVBuilderController
{
	@Autowired
	ServiceImpl serviceImpl;

	
	@GetMapping("/build") //localhost:8080/cvBuilder/build
	public String makeCVGet(CV cv) {
		return "data_input";
	}
	
	@PostMapping("/build")
	public String makeCVPost(@Valid CV cv, BindingResult result) {
		
		System.out.println(cv);
		
		if(!result.hasErrors()) {
			serviceImpl.insertCV(cv);
			return "redirect:/cvBuilder/job";
		}
		else {
			return "data_input";
		}
	}
	
	@GetMapping("/job") //localhost:8080/cvBuilder/job
	public String insertJobExperienceGet(JobExperience jobExperience) {
		return "job-input";
	}
	
	@PostMapping("/job")
	public String insertJobExperiencePost(@Valid JobExperience jobExperience, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/job";
		} else {
			return "job-input";
		}
	}
	
	@PostMapping("/job/cont")
	public String insertJobAndContinuetoEducation(@Valid JobExperience jobExperience, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/edu";
		} else {
			return "job-input";
		}
	}
	 
	@GetMapping("/edu") //localhost:8080/cvBuilder/edu
	public String insertEducation(Education education) {
		return "edu-input";
	}
	
	@PostMapping("/edu")
	public String insertEducationNew(@Valid Education education, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/edu";
		} else {
			return "edu-input";
		}
	}
	
	@PostMapping("/edu/cont")
	public String insertEducationAndContinueToLanguages(@Valid Education education, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/languages";
		} else {
			return "edu-input";
		}
	}
	
	@GetMapping("/languages") //localhost:8080/cvBuilder/languages
	public String insertLanguages(Languages languages) {
		return "languages-input";
	}
	
	@PostMapping("/languages")
	public String insertLanguagesNew(@Valid Languages languages, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/languages";
		} else {
			return "languages-input";
		}
	}
	
	
	@PostMapping("/languages/cont")
	public String insertLanguagesAndContinueToCreation(@Valid Languages languages, BindingResult result) {
		if(!result.hasErrors()) {
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/done";
		} else {
			return "languages-input";
		}
	}
	
	/* obsolete
	@GetMapping("/showdata") //localhost:8080/cvBuilder/showdata
	public String show(Model model) {
		model.addAttribute("innerObj", serviceImpl.selectAllCVs());
		return "show";
	}
	*/
	
	@GetMapping("/done")
	public String sayCVIsDone() throws IOException
	{
		serviceImpl.createPDF(1);
		return "download";
	}
	
	@RequestMapping("/download") ////localhost:8080/cvBuilder/download
	public void givePDF(HttpServletRequest request, HttpServletResponse response)
	{
		String dataDirectory = "C:\\PDF";
		//String dataDirectory = request.getSession().getServletContext().getRealPath("/Files/");
		//System.out.println(dataDirectory);
		Path file = Paths.get(dataDirectory, "MyCv.pdf");
		if (Files.exists(file))
		{
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename ="+"MyCv.pdf");
			try
			{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		//return "download";
	}

	//used for testing currently obsolete
	
	/*
	@GetMapping("/test")
	public String makeFile() throws IOException {
		serviceImpl.createPDF();
		return "hello";
	}
	*/
}
