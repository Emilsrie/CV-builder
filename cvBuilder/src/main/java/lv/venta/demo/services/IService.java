package lv.venta.demo.services;

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

	void insertEducation(String schoolName, String fieldOfStudy, String location, String degree, Date startDate,
			Date endDate);

	void insertJobExperience(String jobTitle, String jobInfo, String companyName, String location, Date startDate,
			Date endDate);

	void insertLanguage(String language, String speaking, String understanding, String writing);

	ArrayList<CV> selectAllCVs();
	
	public void insertCV(CV cv);

}
