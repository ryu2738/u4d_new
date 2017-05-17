package com.chahoo.u4d.api.accounts;

import com.chahoo.u4d.U4DApplication;
import com.chahoo.u4d.api.BaseControllerTest;
import com.chahoo.u4d.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jjryu on 2017-02-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= U4DApplication.class)
@Transactional
public class AccountControllerTest extends BaseControllerTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void 계정생성() throws Exception {
        AccountDto.Create createDto = makeAccount("admin","관리자", "password");

      //  when()
        ResultActions result=mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.loginId", is("admin")));
        result.andExpect(jsonPath("$.loginId").value("admin"));


    }

    @Test
    public void 계정생성_생성정보오류() throws Exception {
        AccountDto.Create createDto = makeAccount("   ", "", "123");
        ResultActions result=mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 계정중복생성()throws Exception {
        AccountDto.Create createDto = makeAccount("admin","관리자", "password");

        ResultActions result=mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isCreated());

        result=mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 계정조회_목록()throws Exception{

        AccountDto.Create createDto = accountCreateDto();
        accountService.createAccount(createDto);

        ResultActions result = mockMvc.perform(get("/api/accounts/")
                .with(httpBasic(createDto.getLoginId(), createDto.getPassword())));

        result.andDo(print());
        result.andExpect(status().isOk());
    }
    @Test
    public void 계정수정() throws Exception {
        AccountDto.Create createDto = accountCreateDto();
        Account account = accountService.createAccount(createDto);

        AccountDto.Update updateDto = new AccountDto.Update();

        updateDto.setLoginId("admin");
        updateDto.setPassword("updatepass01");
        updateDto.setUserName("이름변경");
        System.out.println("==============================");
        System.out.println(account.getLoginId());
        ResultActions result = mockMvc.perform(put("/api/accounts/" + account.getLoginId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto))
                .with(httpBasic(createDto.getLoginId(), createDto.getPassword())));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.loginId", is("admin")));
        result.andExpect(jsonPath("$.userName", is("이름변경")));
    }

    @Test
    public void 계정삭제() throws Exception {
        AccountDto.Create createDto = accountCreateDto();
        Account account = accountService.createAccount(createDto);

        ResultActions result = mockMvc.perform(delete("/api/accounts/12839187241")
                .with(httpBasic(createDto.getLoginId(), createDto.getPassword())));

        result.andDo(print());
        result.andExpect(status().isBadRequest());

        result = mockMvc.perform(delete("/api/accounts/" + account.getLoginId())
                .with(httpBasic(createDto.getLoginId(), createDto.getPassword())));

        result.andDo(print());
        result.andExpect(status().isNoContent());
    }


    private AccountDto.Create accountCreateDto() {
        AccountDto.Create createDto = new AccountDto.Create();
        createDto.setLoginId("admin");
        createDto.setUserName("사용자 이름");
        createDto.setPassword("password");
        return createDto;
    }

    private AccountDto.Create makeAccount(String loginId, String userName, String password) {
        AccountDto.Create createDto=  new AccountDto.Create();
        createDto.setLoginId(loginId);
        createDto.setUserName(userName);
        createDto.setPassword(password);
        return createDto;
    }
}