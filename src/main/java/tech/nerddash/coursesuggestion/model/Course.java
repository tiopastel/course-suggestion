package tech.nerddash.coursesuggestion.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "COURSES", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Course extends AbstractEntityClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9182334921533574136L;
	
	@NotNull
	private Level level = Level.UPPER;
	
	@Expose(deserialize = false)
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Discipline> disciplines = new ArrayList<Discipline>();
		
	public enum Level {
		PRIMARY, LOWER, UPPER
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	@Override
	public String toString() {
		return "Course [level=" + level + ", disciplines=" + disciplines + "]";
	}
	
	

}
