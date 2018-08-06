package by.epam.tote.util;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordSecurity {

	/**
	 * Make password.
	 *
	 * @param password
	 * @return hashed password
	 */
	public static String makePassword(String password) {

		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	/**
	 * Check password.
	 *
	 * @param candidate
	 * @param hashed
	 * @return true, if successful
	 */
	public static boolean checkPassword(String candidate, String hashed) {

		return BCrypt.checkpw(candidate, hashed);
	}

}
