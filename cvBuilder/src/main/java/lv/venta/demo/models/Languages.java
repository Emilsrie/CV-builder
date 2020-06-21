package lv.venta.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Language skills
@Entity
@Table(name="LanguageTable")
public class Languages {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "L_ID")
	private int l_id;
	
	@Column(name = "Language")
	private String language;
	
	@Column(name = "Speaking")
	private String speaking;

	@Column(name = "Understanding")
	private String understanding;
	
	@Column(name = "Writing")
	private String writing;
	
	@ManyToOne
	@JoinColumn(name = "C_ID")
	private CV cv;

	public Languages() {
	}
	
	public Languages(String language, String speaking, String understanding, String writing, CV cv) {
		super();
		this.language = language;
		this.speaking = speaking;
		this.understanding = understanding;
		this.writing = writing;
		this.cv = cv;
	}

	

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSpeaking() {
		return speaking;
	}

	public void setSpeaking(String speaking) {
		this.speaking = speaking;
	}

	public String getUnderstanding() {
		return understanding;
	}

	public void setUnderstanding(String understanding) {
		this.understanding = understanding;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	@Override
	public String toString() {
		return "Languages [language=" + language + ", speaking=" + speaking + ", understanding=" + understanding
				+ ", writing=" + writing + ", cv=" + cv + "]";
	}
}
