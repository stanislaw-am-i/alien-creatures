package lt.ehu.student.aliencreatures.util;

import lt.ehu.student.aliencreatures.controller.Controller;
import lt.ehu.student.aliencreatures.dao.connection.ConnectionCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Properties;

public class EncryptionUtil {
    private static final Logger LOGGER = LogManager.getLogger(EncryptionUtil.class);
    private static final String ALGORITHM_TO_HASH = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS_TO_HASH = 32;
    private static final int KEY_LENGTH_OF_HASH = 512;

    public static String doHashingWithSalt(String value)  {
        // todo: salt must be unique for each user and stored in postgres.
        try {
            Properties prop = new Properties();
            prop.load(EncryptionUtil.class.getClassLoader().getResourceAsStream(ConnectionCreator.PROPERTIES));
            final String salt = (String) prop.get("db.salt");

            PBEKeySpec keySpec = new PBEKeySpec(value.toCharArray(), salt.getBytes(), ITERATIONS_TO_HASH, KEY_LENGTH_OF_HASH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM_TO_HASH);
            byte[] hashed = skf.generateSecret(keySpec).getEncoded();
            String hashedStr = Base64.getEncoder().encodeToString(hashed);

            return hashedStr;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            LOGGER.error("Failed to hash value.", e);
            throw new RuntimeException(e);
        }
    }

}
