package lv.venta.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

//Education for person
@Entity
@Table(name="EducationTable")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "E_ID")
	private int e_id;
	
	@Column(name = "School_name")
	private String school_name;
	
	@Column(name = "Field_of_study")
	private String field_of_study;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "Degree")
	private String degree;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Startdate")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Enddate")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "C_ID")
	private CV cv;

	public Education() {
	}
	
	public Education(String school_name, String field_of_study, String location, String degree, Date startDate,
			Date endDate, CV cv) {
		super();
		this.school_name = school_name;
		this.field_of_study = field_of_study;
		this.location = location;
		this.degree = degree;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cv = cv;
	}

	

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getField_of_study() {
		return field_of_study;
	}

	public void setField_of_study(String field_of_study) {
		this.field_of_study = field_of_study;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		//Validation. Maybe remake to Calendar
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		//Validation. Maybe remake to Calendar
		this.endDate = endDate;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	@Override
	public String toString() {
		return "Education [school_name=" + school_name + ", field_of_study=" + field_of_study + ", location=" + location
				+ ", degree=" + degree + ", startDate=" + startDate + ", endDate=" + endDate + ", cv=" + cv + "]";
	}
}
