# CONFIGMANAGER
details configmanager in java


## Replace in 
```Java
private static final String CONFIG_FOLDER_PATH = "Config";
```

## Usage :
```Java
public class Main {
    public static void main(String[] args) {
        // JSON dosyası için örnek kullanım
        ConfigManager.ConfigType jsonConfigType = ConfigManager.ConfigType.JSON;
        String jsonKey = "json_username";

        ConfigManager.setConfig(jsonConfigType, jsonKey, "john_doe_json");

        String jsonUsername = ConfigManager.getString(jsonKey, jsonConfigType);
        System.out.println("JSON Username: " + jsonUsername);

        // YAML dosyası için örnek kullanım
        ConfigManager.ConfigType yamlConfigType = ConfigManager.ConfigType.YAML;
        String yamlKey = "yaml_username";

        ConfigManager.setConfig(yamlConfigType, yamlKey, "john_doe_yaml");

        String yamlUsername = ConfigManager.getString(yamlKey, yamlConfigType);
        System.out.println("YAML Username: " + yamlUsername);

        // XML dosyası için örnek kullanım
        ConfigManager.ConfigType xmlConfigType = ConfigManager.ConfigType.XML;
        String xmlKey = "xml_username";

        ConfigManager.setConfig(xmlConfigType, xmlKey, "john_doe_xml");

        String xmlUsername = ConfigManager.getString(xmlKey, xmlConfigType);
        System.out.println("XML Username: " + xmlUsername);
    }
}
```
