package com.chahoo.u4d.api.accounts;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by jjryu on 2017-02-07.
 */
public class AccountDto {
    @Data
    public static class Create{
        private Long serNo;
        @NotBlank // not null, not space, not ""
        private String loginId;
        @NotBlank // not null, not space, not ""
        private String userName;
        @NotBlank
        @Size(min=6)
        private String password;
    }

    @Data
    public static class Response{
        private Long serNo;
        private String loginId;
        private String userName;
       // private String password;
        private String email;
        private Date createDate;
        private Date updateDate;
    }

    @Data
    public static class Update {
        private String loginId;
        private String password;
        private String userName;
    }
}
