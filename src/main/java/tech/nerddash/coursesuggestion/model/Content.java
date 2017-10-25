package tech.nerddash.coursesuggestion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CONTENTS")
public class Content extends AbstractEntityClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220120574156561470L;

	@NotNull
	@NotEmpty
	private String name;

	private String description;

	@Lob
	@Column(length = 512)
	private String justification;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", name=" + name + ", description=" + description + ", justification="
				+ justification + "]";
	}

}
