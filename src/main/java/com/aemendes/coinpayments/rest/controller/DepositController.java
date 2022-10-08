package com.aemendes.coinpayments.rest.controller;

import com.aemendes.coinpayments.model.Currency;
import com.aemendes.coinpayments.model.coinpayments.response.BaseResponse;
import com.aemendes.coinpayments.model.coinpayments.response.DepositAddress;
import com.aemendes.coinpayments.model.dto.DepositAddressDTO;
import com.aemendes.coinpayments.rest.service.CoinpaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class DepositController {

    private final CoinpaymentsService coinpaymentsService;

    @Autowired
    public DepositController(CoinpaymentsService coinpaymentsService) {
        this.coinpaymentsService = coinpaymentsService;
    }

    @GetMapping(path = "/deposit-address/{currency}")
    public DepositAddressDTO getDepositAddress(@PathVariable Currency currency) {
        BaseResponse<DepositAddress> depositAddress = this.coinpaymentsService.getDepositAddress(currency.name());

        return DepositAddressDTO.builder()
                .address(depositAddress.getResult().getAddress())
                .build();
    }
}
