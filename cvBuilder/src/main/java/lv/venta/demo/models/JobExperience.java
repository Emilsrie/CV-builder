package lv.venta.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//Job experience for person
@Entity
@Table(name="JobExperienceTable")
public class JobExperience {
	String job_title;
	String job_info;
	String company_name;
	String location;
	
	//Starting date
	//Ending date
}
