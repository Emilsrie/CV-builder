package lv.venta.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//Education for person
@Entity
@Table(name="EducationTable")
public class Education {
	String school_name;
	String field_of_study;
	String location;
	String degree;
	
	//Starting date
	//Ending date
}
