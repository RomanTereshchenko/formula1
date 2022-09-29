package com.foxminded.javaspring;

import java.time.Duration;
import java.util.Comparator;

class Racer {
	
	String abbr;
	String name;
	String team;
	Duration result;
	
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Duration getResult() {
		return result;
	}
	public void setResult(Duration result) {
		this.result = result;
	}
	
	
	public Racer(String abbr, String name, String team, Duration result) {
		super();
		this.abbr = abbr;
		this.name = name;
		this.team = team;
		this.result = result;
	}
	
	public static void main(String[] args) {

	}
	@Override
	public String toString() {
		return "Racer [name=" + name + "]";
	}


}
