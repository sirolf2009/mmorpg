package com.sirolf2009.mmorpg.entity;

import java.util.Set;

import lombok.Data;

@Data
public class User {

	private String user_name;
	private double balance;
	private String first_name;
	private String last_name;
	private String iban;
	private int character_slots;
	private String last_payment;
	private int months_payed;
	private String password;
	private boolean banned;
	private Set<Character> characters;

}