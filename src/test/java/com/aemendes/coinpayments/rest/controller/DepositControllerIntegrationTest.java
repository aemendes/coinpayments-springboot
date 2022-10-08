package com.aemendes.coinpayments.rest.controller;

import com.aemendes.coinpayments.helpers.CoinpaymentsMocks;
import com.aemendes.coinpayments.helpers.WireMockConfig;
import com.aemendes.coinpayments.rest.service.CoinpaymentsService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {WireMockConfig.class})
public class DepositControllerIntegrationTest {
    @Autowired
    CoinpaymentsService coinpaymentsService;

    @Autowired
    private WireMockServer mockCoinpaymentsService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws IOException {
        CoinpaymentsMocks.mock_coinpayments_get_deposit_address_with_valid_hmac(mockCoinpaymentsService);
    }

    @Test
    public void it_should_get_deposit_address() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/deposit-address/btc")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("{\"address\":\"badjoras\"}"));
    }

    @Test
    public void it_should_not_get_deposit_address_with_invalid_currency() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/deposit-address/badjoras")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(content()
                        .string("{\"errorType\":\"INVALID_CURRENCY\",\"message\":\"Invalid Currency badjoras\"}"));
    }
}
