package com.aemendes.coinpayments.client;

import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "coinpayments", url = "${coinpayments.url}")
public interface CoinpaymentsClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    BaseResponse<DepositAddress> getDepositAddress(@RequestBody String requestBody);

}
