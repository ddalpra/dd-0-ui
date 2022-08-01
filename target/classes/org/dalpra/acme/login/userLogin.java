package org.dalpra.acme.login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class userLogin implements Serializable {

	private String username;
	private String password;
	private String accessToken;
	private String renewToken;
	private String nomeUtente;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRenewToken() {
		return renewToken;
	}
	public void setRenewToken(String renewToken) {
		this.renewToken = renewToken;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String login() {
		if(username.equals("admin")){
			if(password.equals("admin")){
				return "index";
			}
			else{
				return "failure";
			}
		}else{
			return "failure";
		}

	}


}
