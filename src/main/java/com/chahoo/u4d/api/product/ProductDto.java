package com.chahoo.u4d.api.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by jjryu on 2017-03-06.
 */

public class ProductDto {
    @Data
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String prodNm;
        private String mtrl;
        private String manComNm;
        private String descr;
        private String pipeDiaDescr;
        @NotNull
        private BigDecimal pipeDia;

        public Create() {
        }
        public Create(String prodNm, BigDecimal pipeDia){
            this.prodNm=prodNm;
            this.pipeDia=pipeDia;
        }
    }
    @Data
    public static class Response {
        private Long prodSerno;
        private String prodNm;
        private String mtrl;
        private String manComNm;
        private String descr;
        private String pipeDiaDescr;
        private BigDecimal pipeDia;
    }
}
