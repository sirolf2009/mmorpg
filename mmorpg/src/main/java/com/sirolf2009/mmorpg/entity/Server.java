package com.sirolf2009.mmorpg.entity;

import lombok.Data;

@Data
public class Server {

	private String address;
	private String name;
	private String location;
	private Integer max_users;
	private Integer connected_users;

}
