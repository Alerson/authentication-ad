package com.authentication.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LdapUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615500675574616120L;

	private String firstName;
	private String firstNameAndLastName;
	private String emailAddress;
	private String department;
	private String company;
	private String groupName;

}
