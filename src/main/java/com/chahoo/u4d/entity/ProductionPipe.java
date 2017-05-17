package com.chahoo.u4d.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jjryu on 2017-02-28.
 */
@Entity
@Data
@AllArgsConstructor
public class ProductionPipe extends Production {
    @Id
    @GeneratedValue
    private Long prodSerno;
    @NotBlank
    private ObstKind obstKind;//관로종류

    private String mtrl;
    private String manComNm;
    private String descr;
    private String pipeDiaDescr;
    private BigDecimal pipeDia;

    public ProductionPipe() {
    }
    public ProductionPipe(String prodNm, BigDecimal pipeDia){
        this.prodNm=prodNm;
        this.pipeDia=pipeDia;
    }
}
