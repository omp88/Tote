package by.epam.tote.entity;

import java.math.BigDecimal;

import by.epam.tote.util.PasswordSecurity;


public class Client extends Entity{
	
	/** The id. */
	private int id;
	
	/** The first name. */
	private String firstName; 
	
	/** The last name. */
	private String lastName; 
	
	/** The login. */
	private String login; 
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/** The hashed password. */
	private String hashedPassword;
	
	/** The passport id. */
	private String passportId;
	
	/** The money. */
	private BigDecimal money;
	
	
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	/**
	 * Gets the passport id.
	 *
	 * @return the passport id
	 */
	public String getPassportId() {
		return passportId;
	}
	
	/**
	 * Sets the passport id.
	 *
	 * @param passportId the new passport id
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	/**
	 * Gets the money.
	 *
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * Sets the money.
	 *
	 * @param money the new money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	

	
	
	

}
