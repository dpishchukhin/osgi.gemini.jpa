package at.emundo.osgi.module2;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntityTwo {
	@Id
	private Long id;

	private String name;

	EntityTwo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
