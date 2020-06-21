package lv.venta.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lv.venta.demo.models.CV;

@Controller
@RequestMapping("/cvBuilder")
public class CVBuilderController
{
	
	@GetMapping("/build")
	public String makeCVGet(CV cv)
	{
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
	
	@GetMapping("/download")
	public String givePDF()
	{
		return "download";
	}

}
