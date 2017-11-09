package tech.nerddash.coursesuggestion.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTENTS")
public class Content extends AbstractEntityClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220120574156561470L;

	@NotNull
	@ManyToOne
	private Discipline discipline;


	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	@Override
	public String toString() {
		return "Content [discipline=" + discipline + ", id=" + id + ", name=" + name + ", description=" + description
				+ ", justification=" + justification + "]";
	}



}
