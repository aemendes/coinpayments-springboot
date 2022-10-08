package com.aemendes.coinpayments.rest.service;

import com.aemendes.coinpayments.client.CoinpaymentsClient;
import com.aemendes.coinpayments.configuration.CoinpaymentsProperties;
import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;
import com.aemendes.coinpayments.rest.service.impl.CoinpaymentsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CoinpaymentsServiceImpl.class)
public class CoinpaymentsServiceImplTest {

    @Mock
    CoinpaymentsClient coinpaymentsClient;

    @Mock
    CoinpaymentsProperties coinpaymentsProperties;

    @InjectMocks
    CoinpaymentsServiceImpl coinpaymentsService;

    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void itShouldGetDepositAddress() {
        // Given
        String currency = "btc";
        DepositAddress depositAddress = new DepositAddress("wallet", "pub_key", "dest_tag");
        BaseResponse<DepositAddress> baseResponse = new BaseResponse<>("ok", depositAddress);

        // When
        when(coinpaymentsClient.getDepositAddress(anyString())).thenReturn(baseResponse);
        when(coinpaymentsProperties.getVersion()).thenReturn("1");
        when(coinpaymentsProperties.getPublicKey()).thenReturn("pub_key");
        when(coinpaymentsProperties.getPrivateKey()).thenReturn("priv_key");
        BaseResponse<DepositAddress> result = coinpaymentsService.getDepositAddress(currency);

        // Then
        Assertions.assertEquals("ok", result.getError());
        Assertions.assertEquals("wallet", result.getResult().getAddress());
        Assertions.assertEquals("pub_key", result.getResult().getPublicKey());
        Assertions.assertEquals("dest_tag", result.getResult().getDestinationTag());
    }
}
