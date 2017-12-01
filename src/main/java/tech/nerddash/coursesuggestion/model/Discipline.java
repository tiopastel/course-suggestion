package tech.nerddash.coursesuggestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DICIPLINES")
public class Discipline extends AbstractEntityClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 902477685423812135L;

	@NotNull
	@ManyToOne
	private Course course;
	

	@OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Content> contents = new ArrayList<Content>();
	
	public Course getCourse() {
		return course;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	@Override
	public String toString() {
		return "Discipline [course=" + course + ", id=" + id + ", name=" + name
				+ ", description=" + description + ", justification=" + justification + "]";
	}


	
	

}
