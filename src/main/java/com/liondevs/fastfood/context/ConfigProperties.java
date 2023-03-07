package com.liondevs.fastfood.context;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@RefreshScope
@ConfigurationProperties(value = "defaultnames")
public class ConfigProperties {
    private Map<String, String> names;

    public Map<String, String> getNames() {
        return names;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "names=" + names +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigProperties that = (ConfigProperties) o;

        return Objects.equals(names, that.names);
    }

    @Override
    public int hashCode() {
        return names != null ? names.hashCode() : 0;
    }
}
