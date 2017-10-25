package tech.nerddash.coursesuggestion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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


	@NotNull @NotEmpty
	@Column(unique = true)
	private String name;
	
	@OneToMany(targetEntity = Discipline.class, cascade=CascadeType.ALL, orphanRemoval=true)
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

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", disciplines=" + disciplines + ", level=" + level + "]";
	}
	
	

}
