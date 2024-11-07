package junior_tech_troopers.backendtroopers.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptFunctions {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean comparePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
