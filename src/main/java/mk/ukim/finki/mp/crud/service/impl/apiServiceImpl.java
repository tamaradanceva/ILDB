package mk.ukim.finki.mp.crud.service.impl;

import mk.ukim.finki.mp.crud.service.apiService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SimpleBindRequest;

@Service
@Transactional
public class apiServiceImpl implements apiService {

	@Override
	public LDAPConnection openConnection(String hostname, int port) {
		LDAPConnection ldapConnection=null;
		try {
			ldapConnection= new LDAPConnection("localhost",10389);
			ldapConnection.connect("localhost", 10389);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ldapConnection;
	}

	@Override
	public boolean closeConnection(LDAPConnection connection) {
		connection.close();
		return connection.isConnected();
	}

	@Override
	public BindResult binduser(LDAPConnection connection, String bindDN,String bindPass) {
		BindResult br=null;
		try {
			br=connection.bind(new SimpleBindRequest(bindDN, bindPass));
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}

	@Override
	public LDAPConnection unbinduser(LDAPConnection connection, String bindDN,
			String bindPass) {
		
		return null;
	}

		
}
