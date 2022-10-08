package com.aemendes.coinpayments.rest.controller;

import com.aemendes.coinpayments.model.Currency;
import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;
import com.aemendes.coinpayments.model.dto.DepositAddressDTO;
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

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DepositController.class)
public class DepositControllerTest {
    @Mock
    CoinpaymentsServiceImpl coinpaymentsService;

    @InjectMocks
    DepositController depositController;

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
        final DepositAddress depositAddress = new DepositAddress("some_address", "1", "2");
        final BaseResponse<DepositAddress> baseResponse = new BaseResponse<>("ok", depositAddress);

        final DepositAddressDTO expectedResult = DepositAddressDTO.builder().address("some_address").build();

        // When
        when(coinpaymentsService.getDepositAddress(Currency.btc.name())).thenReturn(baseResponse);
        DepositAddressDTO response = depositController.getDepositAddress(Currency.btc);

        // Then
        Assertions.assertEquals(expectedResult, response);
    }
}
