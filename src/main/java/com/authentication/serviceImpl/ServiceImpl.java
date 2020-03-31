package com.authentication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.dao.Dao;
import com.authentication.model.LdapUser;
import com.authentication.service.Service;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

	@Autowired
	Dao dao;

	@Override
	public LdapUser getUserDetailFromAd(String login, String password) {
		return dao.getUserDetailFromAd(login, password);
	}


}
