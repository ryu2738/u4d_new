package com.chahoo.u4d.api.product;

import com.chahoo.u4d.entity.ProductionPipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jjryu on 2017-02-28.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductionPipe, Long>{

}
