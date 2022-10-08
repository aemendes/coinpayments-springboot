package com.aemendes.coinpayments.helpers;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class CoinpaymentsMocks {

    public static void mock_coinpayments_get_deposit_address_with_valid_hmac(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/"))
                .withHeader("Content-Type", matching(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .withHeader("HMAC", matching("b6c9d77ad18008f7b926f3eb79566c276727c36c7ecef4621a930f5e778eee3ad3c674920ba038c808485c1f62c2163d7105efe03e674207a14ffe8ff34be1ca"))
                .withRequestBody(matching("currency=btc&version=1&cmd=get_deposit_address&key=PUB_KEY"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(copyToString(
                                CoinpaymentsMocks.class
                                        .getClassLoader()
                                        .getResourceAsStream("payloads/deposit-address.json"),
                                defaultCharset()))));
    }

}
