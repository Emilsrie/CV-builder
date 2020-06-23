package lv.venta.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import lv.venta.demo.models.CV;
import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;

public interface IService {

	ArrayList<JobExperience> selectAllJobExperiences();

	ArrayList<Education> selectAllEducations();

	ArrayList<Languages> selectAllLanguages();

	public void insertCV(CV cv);
	
	void insertEducation(Education edu);
	
	void insertJobExperience(JobExperience jExp);

	void insertLanguage(Languages lang);

	ArrayList<CV> selectAllCVs();
	
	void clearEducation();
	
	void clearJobs();
	
	void clearLanguages();
	
	void clearCV();

	void createPDF() throws IOException;
}
