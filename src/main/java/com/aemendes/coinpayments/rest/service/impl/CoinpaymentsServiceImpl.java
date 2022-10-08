package com.aemendes.coinpayments.rest.service.impl;

import com.aemendes.coinpayments.client.CoinpaymentsClient;
import com.aemendes.coinpayments.configuration.CoinpaymentsProperties;
import com.aemendes.coinpayments.model.Cmd;
import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;
import com.aemendes.coinpayments.model.coinpayments.resquest.RequestBody;
import com.aemendes.coinpayments.rest.service.CoinpaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinpaymentsServiceImpl implements CoinpaymentsService {

    private final CoinpaymentsProperties coinpaymentsProperties;
    private final CoinpaymentsClient coinpaymentsClient;

    @Autowired
    public CoinpaymentsServiceImpl(CoinpaymentsProperties coinpaymentsProperties,
                                   CoinpaymentsClient coinpaymentsClient) {
        this.coinpaymentsProperties = coinpaymentsProperties;
        this.coinpaymentsClient = coinpaymentsClient;
    }

    @Override
    public BaseResponse<DepositAddress> getDepositAddress(String currency) {
        final String requestBody = new RequestBody(coinpaymentsProperties.getVersion(), Cmd.DEPOSIT_ADDRESS.getCmd(),
                currency, coinpaymentsProperties.getPublicKey()).toString();

        return coinpaymentsClient.getDepositAddress(requestBody);
    }
}
