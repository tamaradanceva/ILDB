package mk.ukim.finki.mp.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contributor {
	
	@Id
	@GeneratedValue
	private int contributor_id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "contributor",cascade=CascadeType.ALL)
	private List<Server> servers;

	public int getContributor_id() {
		return contributor_id;
	}

	public void setContributor_id(int contributor_id) {
		this.contributor_id = contributor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
	
}
