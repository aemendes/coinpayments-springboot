package com.aemendes.coinpayments.model.coinpayments.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DepositAddress {
    private String address;

    @JsonProperty("pubkey")
    private String publicKey;

    @JsonProperty("dest_tag")
    private String destinationTag;
}
