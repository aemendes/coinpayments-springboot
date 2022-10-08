package com.aemendes.coinpayments;

import com.aemendes.coinpayments.configuration.CoinpaymentsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(CoinpaymentsProperties.class)
public class CoinpaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinpaymentsApplication.class, args);
    }

}
