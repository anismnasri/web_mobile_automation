/**
 * @author Anis Mnasri
 * 
 */
package com.data;

public class FromYAMLData {

	private String url;
	private String login;
	private String password;

	/**
	 * @return the url
	 */
	public String getURL() {
		return url;
	}

	/**
	 * set url
	 * 
	 * @param url
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * set login
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set password
	 * 
	 * @param password
	 */
	public void setLPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "\nurl: " + url + "\nlogin: " + login + "\npassword: " + password + "\n";

	}

}
