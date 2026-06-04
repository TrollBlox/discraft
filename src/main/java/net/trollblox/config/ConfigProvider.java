package net.trollblox.config;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class ConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair<String, ?>> getConfigsList() {
        return configsList;
    }

    private final List<Pair<String, ?>> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configsList.add(keyValuePair);
        configContents += "# " + comment + "\n# default: " + keyValuePair.getSecond() + "\n" +
                keyValuePair.getFirst() + " = " + keyValuePair.getSecond() + "\n\n";
    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}