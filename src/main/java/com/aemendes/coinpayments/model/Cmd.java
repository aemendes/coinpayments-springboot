package com.aemendes.coinpayments.model;

import lombok.Getter;

@Getter
public enum Cmd {
    /**
     * Extend this for other Cmd calls
     */
    DEPOSIT_ADDRESS("get_deposit_address");

    /* Name used on the enumeration */
    public final String cmd;

    Cmd(String cmd) {
        this.cmd = cmd;
    }
}
