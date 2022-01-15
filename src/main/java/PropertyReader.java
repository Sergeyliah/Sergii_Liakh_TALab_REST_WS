import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static Object fetchPropertyValue(String key) throws IOException {
        FileInputStream file = new FileInputStream("./src/main/properties/properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key);
    }
}
