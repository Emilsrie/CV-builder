package lv.venta.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//Language skills
@Entity
@Table(name="LanguageTable")
public class Languages {
	String language;
	String speaking;
	String understanding;
	String writing;
	
}
