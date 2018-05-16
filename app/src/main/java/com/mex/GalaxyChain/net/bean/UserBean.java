package com.mex.GalaxyChain.net.bean;


/**
 * Created by LSJ on 18/3/10.
 */

public class UserBean extends BaseBean {
	private int user_id;
	private String token;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
