package com.chahoo.u4d.api.accounts;

import com.chahoo.u4d.U4DApplication;
import com.chahoo.u4d.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jjryu on 2017-02-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= U4DApplication.class)
@Transactional
public class AccountControllerTest_usingMockito  {
    @Autowired
    WebApplicationContext wac;
    @Autowired
    public ObjectMapper objectMapper;
    public MockMvc mockMvc;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;


    @InjectMocks
    private AccountService mockService;
    @Mock
    private AccountRepository mockRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }
    @Test
    public void 계정생성() throws Exception {
        Account mockAccount=new Account();
        mockAccount.setLoginId("admin");
        when(mockRepository.save(Matchers.any(Account.class))).thenReturn(mockAccount);


        AccountDto.Create createDto = makeAccount("admin","관리자", "password");
        ResultActions result=mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.loginId", is("admin")));
        result.andExpect(jsonPath("$.loginId").value("admin"));
    }


    private AccountDto.Create makeAccount(String loginId, String userName, String password) {
        AccountDto.Create createDto=  new AccountDto.Create();
        createDto.setLoginId(loginId);
        createDto.setUserName(userName);
        createDto.setPassword(password);
        return createDto;
    }
}