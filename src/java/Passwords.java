
import org.mindrot.jbcrypt.BCrypt;

/**
 * Password hashing and validation helper class
 */
public class Passwords {

    private static final int WORKLOAD = 15;

    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainPassword, salt);
    }

    public static boolean checkPassword(String plainPassword, String storedHash) {
        return BCrypt.checkpw(plainPassword, storedHash);
    }
}
