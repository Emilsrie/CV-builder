package lv.venta.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

import lv.venta.demo.models.CV;
import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;
import lv.venta.demo.services.impl.ServiceImpl;


//jdbc:h2:file:~/cv
@Controller
@RequestMapping("/cvBuilder")//localhost:8080/cvBuilder
public class CVBuilderController
{
	@Autowired
	ServiceImpl serviceImpl;
	
	@GetMapping("/test")//localhost:8080/cvBuilder/test
	public String testStuff()
	{
		return "hello";
	}
	
	@GetMapping("/build")//localhost:8080/cvBuilder/build
	public String makeCVGet(CV cv)
	{
		return "data_input";
	}
	
	@PostMapping("/build")
	public String makeCVPost(@Valid CV cv, BindingResult result)
	{
		
		System.out.println(cv);
		
		if(!result.hasErrors())
		{
			serviceImpl.insertCV(cv);
			return "redirect:/cvBuilder/job";
		}
		else
		{
			return "data_input";
		}
	}
	
	@GetMapping("/job") //localhost:8080/cvBuilder/job
	public String insertJobExperienceGet(JobExperience jobExperience)
	{
		return "job-input";
	}
	
	@PostMapping("/job")
	public String insertJobExperiencePost(@Valid JobExperience jobExperience, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/job";
		}
		else
		{
			return "job-input";
		}
	}
	
	@PostMapping("/job/cont")
	public String insertJobAndContinuetoEducation(@Valid JobExperience jobExperience, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertJobExperience(jobExperience);
			return "redirect:/cvBuilder/edu";
		}
		else
		{
			return "job-input";
		}
	}
	
	@GetMapping("/edu")
	public String insertEducation(Education education)
	{
		return "edu-input";
	}
	
	@PostMapping("/edu")
	public String insertEducationNew(@Valid Education education, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/edu";
		}
		else
		{
			return "edu-input";
		}
	}
	
	@PostMapping("/edu/cont")
	public String insertEducationAndContinueToLanguages(@Valid Education education, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertEducation(education);
			return "redirect:/cvBuilder/languages";
		}
		else
		{
			return "edu-input";
		}
	}
	
	@GetMapping("/languages")
	public String insertLanguages(Languages languages)
	{
		return "languages-input";
	}
	
	@PostMapping("/languages")
	public String insertLanguagesNew(@Valid Languages languages, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/languages";
		}
		else
		{
			return "languages-input";
		}
	}
	
	
	@PostMapping("/languages/cont")
	public String insertLanguagesAndContinueToCreation(@Valid Languages languages, BindingResult result)
	{
		if(!result.hasErrors())
		{
			serviceImpl.insertLanguage(languages);
			return "redirect:/cvBuilder/download";
		}
		else
		{
			return "languages-input";
		}
	}
	
	@GetMapping("/showdata")//localhost:8080/cvBuilder/showdata
	public String show(Model model)
	{
		model.addAttribute("innerObj", serviceImpl.selectAllCVs());
		return "show";
	}
	
	@GetMapping("/download")
	public String givePDF(Model model)
	{
		return "show";
	}

	
	@GetMapping("/testfile")
	public String makeFile()
	{
		serviceImpl.putAllDataInFile();
		return "hello";
	}
}
