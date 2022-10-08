package com.aemendes.coinpayments.configuration.interceptor;

import com.aemendes.coinpayments.configuration.CoinpaymentsProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class CoinpaymentsRequestInterceptor implements RequestInterceptor {

    private final CoinpaymentsProperties coinpaymentsProperties;

    @Autowired
    public CoinpaymentsRequestInterceptor(CoinpaymentsProperties coinpaymentsProperties) {
        this.coinpaymentsProperties = coinpaymentsProperties;
    }

    @Override
    public void apply(RequestTemplate template) {
        final String body = new String(template.body(), StandardCharsets.UTF_8);

        template.header("HMAC", generateHmac(body));
    }

    private String generateHmac(String data) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, coinpaymentsProperties.getPrivateKey())
                .hmacHex(data);
    }
}
