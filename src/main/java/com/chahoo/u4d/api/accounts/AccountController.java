package com.chahoo.u4d.api.accounts;

import com.chahoo.u4d.api.accounts.exception.AccountNotFoundException;
import com.chahoo.u4d.api.accounts.exception.UserDuplicatedException;
import com.chahoo.u4d.commons.ErrorResponse;
import com.chahoo.u4d.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jjryu on 2017-02-07.
 */
@RestController // public 메소드에 @ResponseBody포함
@RequestMapping(value="/api")
public class AccountController {
    @Autowired  private AccountService service;
    @Autowired  private AccountRepository repository;
    @Autowired  private ModelMapper modelMapper;

    @RequestMapping(value="/accounts", method= RequestMethod.POST)
    // !! important Valid
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create, BindingResult result){
        if(result.hasErrors()){
            ErrorResponse errorResponse= new ErrorResponse("잘못된 요청","bad.request",null);
            //TODO BindingResult 안에 있는 에러정보 활용하기
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
         Account newAccount=service.createAccount(create);
        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
    }
    //Todo HATEOAS
    @RequestMapping(value="/accounts", method= RequestMethod.GET)
    public ResponseEntity getAccounts(Pageable pageable){
        Page<Account> page=repository.findAll(pageable);
        List<AccountDto.Response> content=page.getContent().stream()
                .map(account->modelMapper.map(account, AccountDto.Response.class))
                .collect(Collectors.toList());
        PageImpl<AccountDto.Response> result = new PageImpl<AccountDto.Response>(content,pageable, page.getTotalElements());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{loginId}", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public AccountDto.Response getAccount(@PathVariable String loginId) {
        Account account = service.getAccount(loginId);
        return modelMapper.map(account, AccountDto.Response.class);
    }

    // 전체 업데이트: PUT

    // 부분 업데이트: PATCH

    @RequestMapping(value = "/accounts/{loginId}", method =RequestMethod.PUT)
    public ResponseEntity updateAccount(@PathVariable String loginId,
                                        @RequestBody @Valid AccountDto.Update updateDto,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account updatedAccount = service.updateAccount(loginId, updateDto);
        return new ResponseEntity<>(modelMapper.map(updatedAccount, AccountDto.Response.class),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{loginId}", method =RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable String loginId) {
        service.deleteAccount(loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserDuplicatedException(UserDuplicatedException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getLoginId() + "] 중복된 login ID 입니다.");
        errorResponse.setCode("duplicated.username.exception");
        return errorResponse;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAccountNotFoundException(AccountNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getLoginId() + "]에 해당하는 계정이 없습니다.");
        errorResponse.setCode("account.not.found.exception");
        return errorResponse;
    }
}
