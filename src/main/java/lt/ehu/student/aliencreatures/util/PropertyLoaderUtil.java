package lt.ehu.student.aliencreatures.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoaderUtil {

    // todo: rename to PropertyLoader
    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertyLoaderUtil.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Properties file not found: " + filePath);
            }
            properties.load(inputStream);
        }

        return properties;
    }

    private PropertyLoaderUtil() {}
}
