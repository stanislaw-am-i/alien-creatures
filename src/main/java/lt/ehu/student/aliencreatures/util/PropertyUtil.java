package lt.ehu.student.aliencreatures.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Properties file not found: " + filePath);
            }
            properties.load(inputStream);
        }

        return properties;
    }

    private PropertyUtil() {}
}
