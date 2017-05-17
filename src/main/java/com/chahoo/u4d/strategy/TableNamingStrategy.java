package com.chahoo.u4d.strategy;

import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;

/**
 * Created by jjryu on 2017-02-20.
 */
public class TableNamingStrategy extends SpringNamingStrategy {
    private static final String TABLE_PREFIX = "tb_";

    @Override
    public String classToTableName(String className) {
        return TABLE_PREFIX +  super.classToTableName(className);
    }

    @Override
    public String tableName(String tableName) {
        return TABLE_PREFIX +  super.tableName(tableName);
    }
}
