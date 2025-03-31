package com.voxloud.provisioning.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProvisioningConfig {

    @Value("${provisioning.domain}")
    private String domain;

    @Value("${provisioning.port}")
    private int port;

    @Value("${provisioning.codecs}")
    private String codecs; // This will be a comma-separated string

    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getCodecs() {
        return codecs;
    }
}
