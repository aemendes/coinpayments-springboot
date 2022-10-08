package com.aemendes.coinpayments.rest.service;

import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;

public interface CoinpaymentsService {
    BaseResponse<DepositAddress> getDepositAddress(String currency);
}
