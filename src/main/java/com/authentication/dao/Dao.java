package com.authentication.dao;

import com.authentication.model.LdapUser;

public interface Dao {

	public LdapUser getUserDetailFromAd(String email, String password);

}
