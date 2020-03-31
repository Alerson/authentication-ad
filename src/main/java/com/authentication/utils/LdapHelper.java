package com.authentication.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LdapHelper {

	@Value("${spring.ldap.urls}")
	private String LDAP_URL;

	@Value("${spring.ldap.base-dn}")
	private String LDAP_BASE_DN;

	@Value("${spring.ldap.search}")
	private String LDAP_BASE_SEARCH;

	public NamingEnumeration<SearchResult> getLdapContext(String email, String password) {
		NamingEnumeration<SearchResult> results = null;
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, email);
		env.put(Context.SECURITY_CREDENTIALS, password);

		// Create the initial context
		InitialDirContext ctx;
		try {
			SearchControls searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctx = new InitialDirContext(env);
			results = ctx.search(LDAP_BASE_SEARCH + "," + LDAP_BASE_DN, "(&(userPrincipalName=" + email + ")(objectclass=user))", searchControls);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return results;
	}
}