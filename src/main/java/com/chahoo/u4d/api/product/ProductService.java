package com.chahoo.u4d.api.product;

import com.chahoo.u4d.api.Service.U4DService;
import com.chahoo.u4d.entity.ProductionPipe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jjryu on 2017-02-28.
 */
@Service
@Transactional
public class ProductService extends U4DService{
    @Autowired  private ProductRepository repository;
    @Autowired  private ModelMapper modelMapper;

    public ProductionPipe saveProduct(final ProductDto.Create createDto) {
        //validateCreateEntity(createDto);
        ProductionPipe prod=modelMapper.map(createDto,ProductionPipe.class);
        return repository.save(prod);
    }
}



