package com.authentication.daoImpl;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.authentication.dao.Dao;
import com.authentication.model.LdapUser;
import com.authentication.utils.LdapHelper;

@Repository
public class DaoImpl implements Dao {

	@Autowired
	LdapHelper ldapHelper;

	@Override
	public LdapUser getUserDetailFromAd(String email, String password) {
		LdapUser ldapUser = null;
		NamingEnumeration<SearchResult> result = ldapHelper.getLdapContext(email, password);
		if (result != null) {
			SearchResult searchResult = result.nextElement();
			try {
				ldapUser = new LdapUser();
				ldapUser.setFirstName(searchResult.getAttributes().get("givenName").get().toString());
				ldapUser.setFirstNameAndLastName(searchResult.getAttributes().get("name").get().toString());
				ldapUser.setEmailAddress(searchResult.getAttributes().get("userPrincipalName").get().toString());
				ldapUser.setDepartment(searchResult.getAttributes().get("department").get().toString());
				ldapUser.setCompany(searchResult.getAttributes().get("company").get().toString());
				String memberOf = searchResult.getAttributes().get("memberof").get(5).toString();
				String groupName = memberOf.substring(memberOf.indexOf("cn=") + 4, memberOf.indexOf(","));
				ldapUser.setGroupName(groupName);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return ldapUser;
	}

}
