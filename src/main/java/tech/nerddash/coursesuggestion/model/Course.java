package tech.nerddash.coursesuggestion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "COURSES")
public class Course extends AbstractEntityClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9182334921533574136L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull @NotEmpty
	private String name;
	
	@ManyToOne
	private List<Discipline> disciplines;
	
	@NotNull
	private Level level = Level.UPPER;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	
	
	public enum Level {
		PRIMARY, LOWER, UPPER
	}


	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}