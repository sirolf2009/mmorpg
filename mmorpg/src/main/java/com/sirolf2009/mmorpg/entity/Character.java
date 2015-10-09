package com.sirolf2009.mmorpg.entity;

import lombok.Data;

@Data
public class Character implements Comparable<Character> {
	
	private String name;
	private String class_type;
	private String race;
	private int level;
	
	@Override
	public int compareTo(Character o) {
		return new Integer(o.level).compareTo(level);
	}

}
