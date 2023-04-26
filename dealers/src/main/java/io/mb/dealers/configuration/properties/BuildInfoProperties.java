package io.mb.dealers.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("info.build")
public record BuildInfoProperties(String artifact, String name, String description, String version) {
}
