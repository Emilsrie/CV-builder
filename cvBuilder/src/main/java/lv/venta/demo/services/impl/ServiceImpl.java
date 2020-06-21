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
	
	@Autowired
	IService service;
	
	@Override
	public ArrayList<JobExperience> selectAllJobExperiences(){
		return service.selectAllJobExperiences();
	}
	
	@Override
	public ArrayList<Education> selectAllEducations(){
		return service.selectAllEducations();
	}
	
	@Override
	public ArrayList<Languages> selectAllLanguages(){
		return service.selectAllLanguages();
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
	public void insertEducation(String schoolName, String fieldOfStudy, String location, String degree, Date startDate, Date endDate) {
		Education edu = new Education(schoolName, fieldOfStudy, location, degree, startDate, endDate);
		educationRepo.save(edu);
	}
	
	@Override
	public void insertJobExperience(String jobTitle, String jobInfo, String companyName, String location, Date startDate, Date endDate) {
		JobExperience jExp = new JobExperience(jobTitle, jobInfo, companyName, location, startDate, endDate);
		jobExperienceRepo.save(jExp);
	}
	
	@Override 
	public void insertLanguage(String language, String speaking, String understanding, String writing) {
		Languages lang = new Languages(language, speaking, understanding, writing);
		languagesRepo.save(lang);
	}
	
	
	
	
	
}
