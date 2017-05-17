package com.chahoo.u4d.api.buildInfo;

import com.chahoo.u4d.U4DApplication;
import com.chahoo.u4d.api.BaseControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jjryu on 2017-02-15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = U4DApplication.class)
@Transactional
public class BuildInfoControllerTest extends BaseControllerTest {

    @Autowired
    private BuildInfoService service;


    @Test
    public void testGetQrList() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/buildInfo/qrs")
                .with(httpBasic("test", "test")));
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void testCreateQr() throws Exception {
        /*ResultActions result = mockMvc.perform(post("/api/buildInfo/qrs")
                .with(httpBasic("test", "test")));

        result.andDo(print());
        result.andExpect(status().isOk());*/

    }

    @Test
    public void testGetQr() throws Exception {

    }

    @Test
    public void testUpdateQr() throws Exception {
    }

    @Test
    public void testDeleteQr() throws Exception {

    }

    @Test
    public void testHandleException() throws Exception {

    }
}