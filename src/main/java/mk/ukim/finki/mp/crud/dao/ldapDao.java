package mk.ukim.finki.mp.crud.dao;

import java.util.List;

import mk.ukim.finki.mp.crud.model.Contributor;
import mk.ukim.finki.mp.crud.model.Server;

public interface ldapDao {

public Server getServerByHostname(String server);
	
	public List<Server> getAllServers();
	
	public void addServer(Server s);
	
	public void modifyServer(Server s);
	
	public void deleteServer(String server);
	
	public Contributor getContributorById(int id);
	
	public List<Contributor> getAllContributors();
	
	public void addContributor(Contributor c);
	
	public void modifyContributor(Contributor c);
	
	public void deleteContributor(int id);
}
