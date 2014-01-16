package mk.ukim.finki.mp.crud.service;

import java.util.List;

import mk.ukim.finki.mp.crud.model.Contributor;
import mk.ukim.finki.mp.crud.model.Server;

public interface ldapService {

	public Server getServerByHostname(String server);
	
	public List<Server> getAllServers();
	
	public void addOrModifyServer(Server s);
	
	public void deleteServer(String server);
	
	public Contributor getContributorById(int id);
	
	public List<Contributor> getAllContributors();
	
	public void addOrModifyContributor(Contributor c);
	
	public void deleteContributor(int id);
	
	public void addServerToContributor(String server, int id);
	
}
