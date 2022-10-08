package com.aemendes.coinpayments.model.coinpayments.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestBody {
    private String version;
    private String cmd;
    private String currency;
    private String key;

    @Override
    public String toString() {
        return String.format("currency=%s&version=%s&cmd=%s&key=%s",
                this.currency,
                this.version,
                this.cmd,
                this.key);
    }
}

