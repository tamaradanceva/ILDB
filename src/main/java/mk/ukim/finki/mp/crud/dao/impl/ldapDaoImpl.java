package mk.ukim.finki.mp.crud.dao.impl;

import java.util.List;

import mk.ukim.finki.mp.crud.dao.ldapDao;
import mk.ukim.finki.mp.crud.model.Contributor;
import mk.ukim.finki.mp.crud.model.Server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ldapDaoImpl implements ldapDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Server getServerByHostname(String server) {
		return (Server) getCurrentSession().get(Server.class, server);
	}

	@Override
	public List<Server> getAllServers() {
		List<Server> res = getCurrentSession().createQuery("from Server").list();
		return res;
	}

	@Override
	public void addServer(Server s) {
		getCurrentSession().save(s);
		
	}
	
	@Override
	public void modifyServer(Server s) {
		Server s1 =getServerByHostname(s.getHostname());
		s1.setHostname(s.getHostname());
		s1.setPortNumber(s.getPortNumber());
		if(s.getContributor()!=null){
			s1.setContributor(s.getContributor());
		}
		getCurrentSession().update(s1);
		
	}

	@Override
	public void deleteServer(String server) {
		Server s = getServerByHostname(server);
		if (s != null) {
			getCurrentSession().delete(s);
		}
		
	}

	@Override
	public Contributor getContributorById(int id) {
		return (Contributor) getCurrentSession().get(Contributor.class, id);
	}

	@Override
	public List<Contributor> getAllContributors() {
		List<Contributor> res = getCurrentSession().createQuery("from Contributor").list();
		return res;
	}

	@Override
	public void addContributor(Contributor c) {
		getCurrentSession().save(c);
		
	}
	
	@Override
	public void modifyContributor(Contributor c) {
		Contributor c1 = getContributorById(c.getContributor_id());
		c1.setEmail(c.getEmail());
		c1.setName(c.getName());
		c1.setPassword(c.getPassword());
		getCurrentSession().update(c1);
		
	}

	@Override
	public void deleteContributor(int id) {
		Contributor c = getContributorById(id);
		if (c != null) {
			getCurrentSession().delete(c);
		}
		
	}
	
}
