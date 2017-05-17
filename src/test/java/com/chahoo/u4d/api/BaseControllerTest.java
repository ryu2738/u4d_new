package com.chahoo.u4d.api;

import com.chahoo.u4d.U4DApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by jjryu on 2017-02-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = U4DApplication.class)
@Transactional
public class BaseControllerTest {
    @Autowired
    WebApplicationContext wac;
    @Autowired
    public ObjectMapper objectMapper;

    public MockMvc mockMvc;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    @Test
    public void nothing(){
    }


}
