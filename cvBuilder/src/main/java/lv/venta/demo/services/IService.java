package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;

public interface IService {

	ArrayList<JobExperience> selectAllJobExperiences();

	ArrayList<Education> selectAllEducations();

	ArrayList<Languages> selectAllLanguages();

}
