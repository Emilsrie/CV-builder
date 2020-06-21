package lv.venta.demo.services.impl;

import java.util.ArrayList;
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
	

	
	
	
	
}
