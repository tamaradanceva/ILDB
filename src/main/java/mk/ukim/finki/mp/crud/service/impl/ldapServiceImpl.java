package mk.ukim.finki.mp.crud.service.impl;

import java.util.List;

import mk.ukim.finki.mp.crud.dao.ldapDao;
import mk.ukim.finki.mp.crud.model.Contributor;
import mk.ukim.finki.mp.crud.model.Server;
import mk.ukim.finki.mp.crud.service.ldapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ldapServiceImpl implements ldapService{

	@Autowired
	private ldapDao ldapDao;
	
	@Override
	public Server getServerByHostname(String server) {
		return ldapDao.getServerByHostname(server);
	}

	@Override
	public List<Server> getAllServers() {
		return ldapDao.getAllServers();
	}

	@Override
	public void addOrModifyServer(Server s) {
		boolean exists = ldapDao.getServerByHostname(s.getHostname()) != null;
		if (exists) {
			ldapDao.modifyServer(s);
		} else {
			ldapDao.addServer(s);
		}
	}

	@Override
	public void deleteServer(String server) {
		ldapDao.deleteServer(server);
	}

	@Override
	public Contributor getContributorById(int id) {
		return ldapDao.getContributorById(id);
	}

	@Override
	public List<Contributor> getAllContributors() {
		return ldapDao.getAllContributors();
	}

	@Override
	public void addOrModifyContributor(Contributor c) {
		boolean exists = ldapDao.getContributorById(c.getContributor_id()) != null;
		if (exists) {
			ldapDao.modifyContributor(c);
		} else {
			ldapDao.addContributor(c);
		}
		
	}

	@Override
	public void deleteContributor(int id) {
		ldapDao.deleteContributor(id);
		
	}

	@Override
	public void addServerToContributor(String server, int id) {
		Contributor c = null;
		Server s = this.getServerByHostname(server);
		if (id >0) {
			c = ldapDao.getContributorById(id);
		}

		if (c != null && s != null) {
			s.setContributor(c);
			this.addOrModifyServer(s);

		}
		
	}

	
}
