package mk.ukim.finki.mp.crud.service;

import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;

public interface apiService {

	public LDAPConnection openConnection(String hostname, int port);
	
	public boolean closeConnection(LDAPConnection connection);
	
	public BindResult binduser(LDAPConnection connection, String bindDN, String bindPass);
	
	public LDAPConnection unbinduser(LDAPConnection connection, String bindDN, String bindPass);
	
}
