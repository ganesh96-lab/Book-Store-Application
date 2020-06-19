package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class Setpassworddto {

	public String password;
	public String cfmpassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCfmpassword() {
		return cfmpassword;
	}
	public void setCfmpassword(String cfmpassword) {
		this.cfmpassword = cfmpassword;
	}
}
