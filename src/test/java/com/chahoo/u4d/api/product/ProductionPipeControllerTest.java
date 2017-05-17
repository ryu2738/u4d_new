package com.chahoo.u4d.api.product;

import com.chahoo.u4d.api.BaseControllerTest;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jjryu on 2017-02-23.
 */
public class ProductionPipeControllerTest extends BaseControllerTest{

    @Test
    public void 제품목록_GET_접속가능_여부() throws Exception {
        //ToDo 제품목록_GET_접속가능_여부 테스트 완성하기
        ResultActions result=mockMvc.perform(get("/api/products"));
        result.andDo(print());
    }
    @Test
    public void 제품생성_Null체크() throws Exception{
        ResultActions result=mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }
    @Test
    public void 제품생성_필수항목체크() throws Exception{

        ProductDto.Create product=new ProductDto.Create("testProduct", null);
        ResultActions result=mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }
    @Test
    public void 제품생성_1개() throws Throwable {
        ProductDto.Create product=makeProduct("testProduct", new BigDecimal(100));
        ResultActions result=mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));
        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.prodNm").value("testProduct"));
    }

    private ProductDto.Create makeProduct(String prodNm,BigDecimal pipeDia){
        ProductDto.Create createDto=new ProductDto.Create();
        createDto.setProdNm(prodNm);
        createDto.setPipeDia(pipeDia);
        return createDto;

    }


}