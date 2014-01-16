package mk.ukim.finki.mp.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mk.ukim.finki.mp.crud.model.Server;
import mk.ukim.finki.mp.crud.service.apiService;
import mk.ukim.finki.mp.crud.service.ldapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionOptions;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ModifyRequest;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchScope;
import com.unboundid.ldif.LDIFException;

@Controller
@SessionAttributes(value = { "hostname", "bindRDN", "bindPass", "port" })
public class LinkController {

	@Autowired
	private apiService apiService;

	@Autowired
	private ldapService ldapService;

	@RequestMapping(value = "/")
	public ModelAndView mainPage() throws LDAPException {
		ModelAndView modview = new ModelAndView("home");
		// try{
		// LDAPConnection ldapConnection=apiService.openConnection("localhost",
		// 10389);
		// BindResult
		// br=apiService.binduser(ldapConnection,"cn=Bla Dancheva+sn=Dancheva+description=admin,ou=admins,dc=appDIT\\ ,dc=example,dc=com",
		// "papagalce");
		// modview.addObject("message", br.toString());
		// ldapConnection.close();
		// }
		// catch(Exception e){
		// modview.addObject("message","Bind not successful");
		// e.printStackTrace();
		// }
		List<Server> lista = ldapService.getAllServers();
		modview.addObject("lista", lista);
		return modview;
	}

	@RequestMapping(value = "/index")
	public ModelAndView indexPage() {
		return new ModelAndView("home");
	}

	
	
	@RequestMapping(value = { "/connected" }, method = RequestMethod.GET)
	public ModelAndView getConnect(HttpSession session){
		ModelAndView mod = new ModelAndView("connected");
		mod.addObject("hostname",session.getAttribute("hostnameChoice"));
		mod.addObject("bindRDN", session.getAttribute("bindRDN"));
		mod.addObject("bindPass", session.getAttribute("bindPass"));
		mod.addObject("port", session.getAttribute("port"));
		return mod;
	}
	
	@RequestMapping(value = { "/connected" }, method = RequestMethod.POST)
	public ModelAndView postConnect(HttpServletRequest request,
			HttpSession session, @RequestParam String hostnameChoice,
			@RequestParam(value = "bindRDN") String bindRDN,
			@RequestParam(value = "bindPass") String bindPass) {
		ModelAndView mod = new ModelAndView("connected");
		mod.addObject("message", hostnameChoice + "!");
		LDAPConnection ldapConnection=null;
		try {

			Server s = ldapService.getServerByHostname(hostnameChoice);
			int port = s.getPortNumber();
			if (s.isAuthentication() == true) {
				String base = s.getBase();
				ldapConnection = apiService.openConnection(
						hostnameChoice, port);
				BindResult br = null;
				mod.addObject("rdn", hostnameChoice);
				mod.addObject("baseime", bindRDN + "," + base);
				br = apiService.binduser(ldapConnection, bindRDN + "," + base,
						bindPass);
				mod.addObject("status", br.toString());
				// dodavanje vo sesija
				mod.addObject("hostname", hostnameChoice);
				mod.addObject("bindRDN", bindRDN);
				mod.addObject("bindPass", bindPass);
				mod.addObject("port", s.getPortNumber());

			} else {
				ldapConnection = apiService.openConnection(
						hostnameChoice, port);
				mod.addObject("status", ldapConnection.isConnected());
				// dodavanje vo sesija
				mod.addObject("hostname", hostnameChoice);
				mod.addObject("bindRDN", bindRDN);
				mod.addObject("bindPass", bindPass);
				mod.addObject("port", s.getPortNumber());
			}

		} catch (Exception e) {
			mod.addObject("status", "Bind not successful");
			e.printStackTrace();
		}
		finally{
			ldapConnection.close();
		}
		return mod;
	}
	
	@RequestMapping(value = { "/addEntry" }, method = RequestMethod.GET)
	public ModelAndView addentryget(HttpSession session){
		ModelAndView mod = new ModelAndView("connected");
		mod.addObject("hostname",session.getAttribute("hostnameChoice"));
		mod.addObject("bindRDN", session.getAttribute("bindRDN"));
		mod.addObject("bindPass", session.getAttribute("bindPass"));
		mod.addObject("port", session.getAttribute("port"));
		return mod;
	}

	@RequestMapping(value = { "/addEntry" }, method = RequestMethod.POST)
	public ModelAndView addentry(HttpServletRequest request,
			HttpSession session, @RequestParam(value = "ldif") String ldif) {

		ModelAndView mod = new ModelAndView("statusIzmena");
		final LDAPConnectionOptions connectionOptions = new LDAPConnectionOptions();
		int connectionTimeoutMillis = 1000;
		connectionOptions.setConnectTimeoutMillis(connectionTimeoutMillis);
		String[] strs = ldif.split("\\r?\\n");
		LDAPConnection ldapConnection = null;
		LDAPResult ldapResult = null;
		try {
			Server s = ldapService.getServerByHostname((String) session
					.getAttribute("hostname"));
			if (s.isAuthentication() == false)
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
			else {
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
				BindResult br = null;
				br = apiService.binduser(
						ldapConnection,
						(String) session.getAttribute("bindRDN") + ","
								+ s.getBase(),
						(String) session.getAttribute("bindPass"));
			}
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ldapResult = ldapConnection.add(new AddRequest(strs));
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LDIFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ldapResult != null)
				mod.addObject("status", ldapResult.toString());
			else
				mod.addObject("status", "The addition was not successful");
			ldapConnection.close();
		}

		return mod;
	}

	@RequestMapping(value = { "/modifyEntry" }, method = RequestMethod.GET)
	public ModelAndView modifyentryget(HttpSession session){
		ModelAndView mod = new ModelAndView("connected");
		mod.addObject("hostname",session.getAttribute("hostnameChoice"));
		mod.addObject("bindRDN", session.getAttribute("bindRDN"));
		mod.addObject("bindPass", session.getAttribute("bindPass"));
		mod.addObject("port", session.getAttribute("port"));
		return mod;
	}
	@RequestMapping(value = { "/modifyEntry" }, method = RequestMethod.POST)
	public ModelAndView modifyentry(HttpServletRequest request,
			HttpSession session, @RequestParam(value = "ldif") String ldif) {

		ModelAndView mod = new ModelAndView("statusIzmena");
		final LDAPConnectionOptions connectionOptions = new LDAPConnectionOptions();
		int connectionTimeoutMillis = 1000;
		connectionOptions.setConnectTimeoutMillis(connectionTimeoutMillis);
		String[] strs = ldif.split("\\r?\\n");
		LDAPConnection ldapConnection = null;
		LDAPResult ldapResult = null;
		try {
			Server s = ldapService.getServerByHostname((String) session
					.getAttribute("hostname"));
			if (s.isAuthentication() == false)
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
			else {
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
				BindResult br = null;
				br = apiService.binduser(
						ldapConnection,
						(String) session.getAttribute("bindRDN") + ","
								+ s.getBase(),
						(String) session.getAttribute("bindPass"));
			}
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ldapResult = ldapConnection.modify(new ModifyRequest(strs));
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LDIFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ldapResult != null)
				mod.addObject("status", ldapResult.toString());
			else
				mod.addObject("status", "The modification was not successful");
			ldapConnection.close();
		}

		return mod;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public @ResponseBody
	String search(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "filter") String filter,
			@RequestParam(value = "base") String base,
			@RequestParam String scope) {
		String result = "";
		final LDAPConnectionOptions connectionOptions = new LDAPConnectionOptions();
		int connectionTimeoutMillis = 1000;
		connectionOptions.setConnectTimeoutMillis(connectionTimeoutMillis);
		LDAPConnection ldapConnection = null;
		LDAPResult ldapResult = null;
		try {
			Server s = ldapService.getServerByHostname((String) session
					.getAttribute("hostname"));
			if (s.isAuthentication() == false)
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
			else {
				ldapConnection = new LDAPConnection(connectionOptions,
						(String) session.getAttribute("hostname"),
						(int) session.getAttribute("port"));
				BindResult br = null;
				br = apiService.binduser(
						ldapConnection,
						(String) session.getAttribute("bindRDN") + ","
								+ s.getBase(),
						(String) session.getAttribute("bindPass"));
			}
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// Filter filter= Filter.createEqualityFilter("objectclass",
			// "document");
			SearchScope scp=null;
			if(scope.equals("SUB")) scp= SearchScope.SUB;
			else if(scope.equals("BASE"))scp= SearchScope.BASE;
			else scp= SearchScope.ONE;
				
			ldapResult = ldapConnection.search(new SearchRequest(
					base,
					scp, filter, "dc", "cn", "objectclass"));
			result = ldapResult.toString();
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (result.equals(""))
			result="Search not successful";
			ldapConnection.close();
		}
		return result;
	}

}
