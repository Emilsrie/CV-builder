package lv.venta.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//Main info about the person
@Entity
@Table(name="CVtable")
public class CV {
	String name;
	String surname;
	String background_information;
	String other_skills;
	String phone_number;
	String address;
	String city;
	String province;
	String zip_code;
	String email;
}
