import java.io.*;
import java.util.Properties;
import org.yaml.snakeyaml.Yaml;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigManager {
    private static final String CONFIG_FOLDER_PATH = "Config";

    public enum ConfigType {
        JSON,
        YAML,
        XML
    }

    public static String getString(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return properties.getProperty(key);
    }

    public static boolean getBool(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getInt(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return Integer.parseInt(properties.getProperty(key));
    }

    public static byte getByte(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return Byte.parseByte(properties.getProperty(key));
    }

    public static char getChar(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return properties.getProperty(key).charAt(0);
    }

    public static float getFloat(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return Float.parseFloat(properties.getProperty(key));
    }

    public static double getDouble(String key, ConfigType configType) {
        Properties properties = loadConfig(configType);
        return Double.parseDouble(properties.getProperty(key));
    }

    private static Properties loadConfig(ConfigType configType) {
        String configFilePath = getConfigFilePath(configType);
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(configFilePath)) {
            switch (configType) {
                case XML:
                    properties.loadFromXML(input);
                    break;
                case JSON:
                case YAML:
                    properties.load(input);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private static String getConfigFilePath(ConfigType configType) {
        File configFolder = new File(CONFIG_FOLDER_PATH);
        if (!configFolder.exists()) {
            configFolder.mkdir();
        }

        return CONFIG_FOLDER_PATH + File.separator + "config." + configType.name().toLowerCase();
    }

    // JSON dosyasına değer atama
    private static void setJsonConfig(String key, String value) {
        JSONObject jsonObject = readJsonConfig();
        jsonObject.put(key, value);

        try (FileWriter fileWriter = new FileWriter(CONFIG_FOLDER_PATH + File.separator + "config.json")) {
            fileWriter.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON dosyasından değer okuma
    private static JSONObject readJsonConfig() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        try (FileReader reader = new FileReader(CONFIG_FOLDER_PATH + File.separator + "config.json")) {
            Object obj = parser.parse(reader);
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    // YAML dosyasına değer atama
    private static void setYamlConfig(String key, String value) {
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(CONFIG_FOLDER_PATH + File.separator + "config.yaml")) {
            yaml.dump(Map.of(key, value), fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // YAML dosyasından değer okuma
    private static String readYamlConfig(String key) {
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(CONFIG_FOLDER_PATH + File.separator + "config.yaml")) {
            Object obj = yaml.load(reader);
            if (obj instanceof Map) {
                Map map = (Map) obj;
                return map.get(key).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // XML dosyasına değer atama
    private static void setXmlConfig(String key, String value) {
        Properties properties = new Properties();
        properties.setProperty(key, value);

        try (OutputStream output = new FileOutputStream(CONFIG_FOLDER_PATH + File.separator + "config.xml")) {
            properties.storeToXML(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // XML dosyasından değer okuma
    private static String readXmlConfig(String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FOLDER_PATH + File.separator + "config.xml")) {
            properties.loadFromXML(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Config dosyasına değer atama
    public static void setConfig(ConfigType configType, String key, String value) {
        switch (configType) {
            case JSON:
                setJsonConfig(key, value);
                break;
            case YAML:
                setYamlConfig(key, value);
                break;
            case XML:
                setXmlConfig(key, value);
                break;
        }
    }
}
