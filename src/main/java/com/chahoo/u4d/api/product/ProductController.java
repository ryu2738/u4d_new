package com.chahoo.u4d.api.product;

import com.chahoo.u4d.commons.ErrorResponse;
import com.chahoo.u4d.entity.ProductionPipe;
import com.chahoo.u4d.exception.InvalidCreateFieldException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by jjryu on 2017-02-23.
 */
@RestController
@RequestMapping(value = "/api")
public class ProductController {
    @Autowired  private ProductService service;
    @Autowired  private ProductRepository repository;
    @Autowired  private ModelMapper modelMapper;

    @RequestMapping(value="/products", method= RequestMethod.GET)
    public ResponseEntity getProductList(Pageable pagable){
        return null;
    }

    @RequestMapping(value="/products", method= RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody @Valid ProductDto.Create create, BindingResult result) {
        ProductionPipe newProductionPipe = null;
        try {
            bindingCheck(result);
            newProductionPipe = saveProduct(create);
        }catch(InvalidCreateFieldException ie){
            ErrorResponse errorResponse= new ErrorResponse("잘못된 요청","bad.request",null);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(modelMapper.map(newProductionPipe, ProductDto.Response.class), HttpStatus.CREATED);


    }

    private ProductionPipe saveProduct(ProductDto.Create create) {
        ProductionPipe newProductionPipe = null;
        try {
            newProductionPipe = service.saveProduct(create);
        } catch (Throwable throwable) {
            throw new InvalidCreateFieldException("필드를 확인하세요");

        }
        return newProductionPipe;
    }

    private void bindingCheck(BindingResult result) {
        if(result.hasErrors()){
            throw new InvalidCreateFieldException("필드를 확인하세요");
        }
    }
}
