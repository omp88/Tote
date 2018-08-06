package by.epam.tote.entity;

import by.epam.tote.util.PasswordSecurity;

public class Admin extends Entity {


	/** The id. */
	private int id;
	
	/** The login. */
	private String login;
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/** The hashed password. */
	private String hashedPassword;
	
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = PasswordSecurity.makePassword(password);
	}
	
	/**
	 * Gets the hashed password.
	 *
	 * @return the hashed password
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	/**
	 * Sets the hashed password.
	 *
	 * @param hashedPassword the new hashed password
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	
	
	
	
}
