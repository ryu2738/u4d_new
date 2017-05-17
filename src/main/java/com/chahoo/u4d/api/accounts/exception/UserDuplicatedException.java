package com.chahoo.u4d.api.accounts.exception;

/**
 * Created by jjryu on 2017-02-14.
 */
public class UserDuplicatedException extends RuntimeException {

    String loginId;

    public UserDuplicatedException(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }
}
