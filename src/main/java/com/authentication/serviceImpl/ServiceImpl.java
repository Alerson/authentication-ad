package com.authentication.serviceImpl;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.model.LdapUser;
import com.authentication.service.Service;
import com.authentication.utils.LdapHelper;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	@Autowired
	LdapHelper ldapHelper;

	@Override
	public LdapUser getUserDetailFromAd(String login, String password) {
		LdapUser ldapUser = new LdapUser();
		NamingEnumeration<SearchResult> result = ldapHelper.getLdapContext(login, password);
		if (result != null) {
			SearchResult searchResult = result.nextElement();
			try {
				ldapUser.setFirstName(searchResult.getAttributes().get("givenName").get().toString());
				ldapUser.setFirstNameAndLastName(searchResult.getAttributes().get("name").get().toString());
				ldapUser.setEmailAddress(searchResult.getAttributes().get("userPrincipalName").get().toString());
				ldapUser.setDepartment(searchResult.getAttributes().get("department").get().toString());
				ldapUser.setCompany(searchResult.getAttributes().get("company").get().toString());
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return ldapUser;
	}


}
