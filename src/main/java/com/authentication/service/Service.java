package com.authentication.service;

import com.authentication.model.LdapUser;

public interface Service {

	public LdapUser getUserDetailFromAd(String email, String password);

}
