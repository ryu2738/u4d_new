package com.chahoo.u4d.api.product;

import com.chahoo.u4d.U4DApplication;
import com.chahoo.u4d.entity.ProductionPipe;
import com.chahoo.u4d.exception.InvalidCreateFieldException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by jjryu on 2017-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= U4DApplication.class)
public class ProductionPipeServiceTest {
   // @injectMocks
   // private ProductService mockService;
    @Mock
    private ProductRepository mockRepository;

    @Autowired
    private ProductService productService;


    private static Validator validator;

    @Autowired
    WebApplicationContext wac;

    public MockMvc mockMvc;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService();
        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        validator=factory.getValidator();


        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void productService_객체생성() throws Exception{
        assertNotNull(productService);
    }
    @Test
    public void productRepository_객체생성() throws Exception{
        assertNotNull(mockRepository);
    }
    //@Test(expected= InvalidCreateFieldException.class)
    @Test
    public void 제품생성_필수항목체크() throws Exception{
        ProductDto.Create product=new ProductDto.Create();
        assertThatThrownBy(()->productService.saveProduct(null))
                .isInstanceOf(InvalidCreateFieldException.class);

        assertThatThrownBy(()->productService.saveProduct(product))
                .isInstanceOf(InvalidCreateFieldException.class);


    }
    @Test
    public void 제품생성_1개() throws Throwable {
        ProductDto.Create product=new ProductDto.Create();
        product.setProdNm("testProduct");
        product.setPipeDia(new BigDecimal(100));
        ProductionPipe createProductionPipe =productService.saveProduct(product);
        assertThat(createProductionPipe.getProdNm(), is("testProduct"));
    }

}
