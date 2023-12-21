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

### Libs:
#### Maven
```xml
<dependencies>
    <!-- JSON library -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version> <!-- Sürüm numarasını güncelleyebilirsiniz -->
    </dependency>

    <!-- SnakeYAML library -->
    <dependency>
        <groupId>org.yaml</groupId>
        <artifactId>snakeyaml</artifactId>
        <version>1.28</version> <!-- Sürüm numarasını güncelleyebilirsiniz -->
    </dependency>

    <!-- Jackson library (for YAML) -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.0</version> <!-- Sürüm numarasını güncelleyebilirsiniz -->
    </dependency>
</dependencies>
```
#### Gradle:
```groovy
dependencies {
    // JSON library
    implementation 'org.json:json:20210307' // Sürüm numarasını güncelleyebilirsiniz

    // SnakeYAML library
    implementation 'org.yaml:snakeyaml:1.28' // Sürüm numarasını güncelleyebilirsiniz

    // Jackson library (for YAML)
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.0' // Sürüm numarasını güncelleyebilirsiniz
}
```
