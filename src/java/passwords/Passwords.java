package passwords;

import org.mindrot.jbcrypt.BCrypt;

/**
 * <pre>
 * Passwords generation and verification helper class.
 * 
 * Uses bcrypt with salt technique to hash and verify passwords.
 * Applies 15 rounds for each password hashing.
 * </pre>
 */
public class Passwords {

    private static final int WORKLOAD = 15;

    /**
     * Bcrypt with salt password hash function
     * 
     * @param plainPassword plain password to be hashed
     * @return salted and hashed password string
     */
    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainPassword, salt);
    }

    /**
     * Checks if the provided password matches hashed password
     * @param plainPassword raw password to be checked if valid
     * @param storedHash hashed password to be validated against
     * @return true if plain password hash matches the hashed password
     *         false otherwise
     */
    public static boolean checkPassword(String plainPassword, String storedHash) {
        return BCrypt.checkpw(plainPassword, storedHash);
    }
}
