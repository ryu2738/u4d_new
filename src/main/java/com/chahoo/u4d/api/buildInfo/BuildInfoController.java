package com.chahoo.u4d.api.buildInfo;

import com.chahoo.u4d.commons.ErrorResponse;
import com.chahoo.u4d.entity.Qr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jjryu on 2017-02-06.
 */
@RestController
@RequestMapping("/api")
public class BuildInfoController {

    @Autowired
    private BuildInfoService service;

    @Autowired
    private BuildInfoRepository repository;


    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value="/buildInfo/qrs", method= RequestMethod.GET)
    public ResponseEntity getQrList(@RequestParam Map<String, String> params,
                                 @PageableDefault(sort = { "qrSerno" }, direction = Sort.Direction.DESC, page = 1)  Pageable pageable){
        Page<Qr> page=repository.findAll(pageable);
        List<BuildInfoDto.Response> content=page.getContent().stream()
                .map(buildInfo->modelMapper.map(buildInfo, BuildInfoDto.Response.class))
                .collect(Collectors.toList());
        PageImpl<BuildInfoDto.Response> result = new PageImpl<BuildInfoDto.Response>(content,pageable, page.getTotalElements());
        return new ResponseEntity(result, HttpStatus.OK);
       // return new ResponseEntity(content, HttpStatus.OK);
    }

    @RequestMapping(value="/buildInfo/qrs", method= RequestMethod.POST)
    public ResponseEntity createQr(@RequestBody Qr qr){
        BuildInfoDto.Response dumpRes=new BuildInfoDto.Response();
        return new ResponseEntity(dumpRes, HttpStatus.OK);

    }
    @RequestMapping(value="/buildInfo/qrs/{qrSerno}", method= RequestMethod.GET)
    public ResponseEntity getQr(@RequestBody String body){
        BuildInfoDto.Response dumpRes=new BuildInfoDto.Response();
        return new ResponseEntity(dumpRes, HttpStatus.OK);

    }
    @RequestMapping(value="/buildInfo/qrs/{qrSerno}", method= RequestMethod.PUT)
    public ResponseEntity updateQr(@RequestBody Qr qr){
        BuildInfoDto.Response dumpRes=new BuildInfoDto.Response();
        return new ResponseEntity(dumpRes, HttpStatus.OK);

    }
    @RequestMapping(value="/buildInfo/qrs/{qrSerno}", method= RequestMethod.DELETE)
    public ResponseEntity deleteQr(@RequestBody Qr qr) throws Exception{
        BuildInfoDto.Response dumpRes=new BuildInfoDto.Response();
        if(true)
            throw new Exception("test");

        return new ResponseEntity(dumpRes, HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("예외 테스트");
        errorResponse.setCode("line.exception");
        return errorResponse;
    }
}
