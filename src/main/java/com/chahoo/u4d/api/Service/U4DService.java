package com.chahoo.u4d.api.Service;

import com.chahoo.u4d.entity.Production;
import com.chahoo.u4d.exception.InvalidCreateFieldException;

import java.lang.reflect.Method;

/**
 * Created by jjryu on 2017-03-03.
 */
//@Service
public class U4DService <T>{

    public void validateCreateEntity (T entity){
        checkNull(entity);
        checkValidate(entity);
    }

    private void checkValidate(T entity) {
        try {
            Method checkValidation  = entity.getClass().getMethod("checkValidation", Production.class );
            checkValidation.invoke(entity, new Object[]{entity});
        } catch(Exception e){
            throw new InvalidCreateFieldException("Field 체크 필요");
        }
    }

    private void checkNull(T entity)  {
        if(entity==null)
            throw new NullPointerException("NULL값이 입력되었습니다.");
    }
}
