package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	
	
	
	
}
