package io.mb.store.configuration.properties;

import lombok.Data;

@Data
public class ExternalApiProperties {
    private String host;

    private String basePath;

    private String protocol = "https";

    private int port = 443;
}
