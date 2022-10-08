package com.aemendes.coinpayments.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Load all Coinpayments configurations from application.yml
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "coinpayments")
public class CoinpaymentsProperties {
    /**
     * Coinpayments Url
     */
    private String url;

    /**
     * Coinpayments Version
     */
    private String version;

    /**
     * Coinpayments Client Public Key
     */
    private String publicKey;

    /**
     * Coinpayments Client Private Key
     */
    private String privateKey;
}
