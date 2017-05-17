package com.chahoo.u4d.api.accounts.exception;

/**
 * Created by jjryu on 2017-02-14.
 */
public class AccountNotFoundException extends RuntimeException {

    String loginId;

    public AccountNotFoundException(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }
}
