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
import lv.venta.demo.services.impl.ServiceImpl;

@Controller
@RequestMapping("/cvBuilder")
public class CVBuilderController
{
	@Autowired
	ServiceImpl serviceImpl;
	
	@GetMapping("/test")
	public String testStuff()
	{
		return "hello";
	}
	
	@GetMapping("/build")
	public String makeCVGet(CV cv)
	{
		//TODO make input fields bigger
		return "data_input";
	}
	
	@PostMapping("/build")
	public String makeCVPost(@Valid CV cv, BindingResult result)
	{
		if(!result.hasErrors())
		{
			//make pdf
			return "redirect:/cvBuilder/download";
		}
		else
		{
			return "data_input";
		}
	}
	
	@GetMapping("/showdata")
	public String show(Model model)
	{
		//model.addAttribute("innerObj", serviceImpl.)
		return "hello";
	}
	
	@GetMapping("/download")
	public String givePDF()
	{
		return "download";
	}

}
