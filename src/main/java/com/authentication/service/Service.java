package com.authentication.service;

import com.authentication.model.LdapUser;

public interface Service {

	public LdapUser getUserDetailFromAd(String login, String password);

}
