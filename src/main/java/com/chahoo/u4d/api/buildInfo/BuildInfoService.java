package com.chahoo.u4d.api.buildInfo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jjryu on 2017-02-07.
 */
@Service
@Transactional
public class BuildInfoService {
    @Autowired
    private BuildInfoRepository repository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

}
