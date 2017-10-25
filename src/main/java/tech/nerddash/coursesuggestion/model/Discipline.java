package tech.nerddash.coursesuggestion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DICIPLINES")
public class Discipline extends AbstractEntityClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 902477685423812135L;

	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(targetEntity = Content.class, cascade = CascadeType.ALL)
	private List<Content> contents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Discipline [id=" + id + ", name=" + name + ", contents=" + contents + "]";
	}

}
