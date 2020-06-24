package com.karthik.test.controller;

import com.karthik.test.model.PhoneNumber;
import com.karthik.test.model.PhoneRequest;
import com.karthik.test.model.PhoneResponse;
import com.karthik.test.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping("/phone")
    public PhoneResponse phoneNumbers(@Valid  @RequestBody PhoneRequest request){
        return phoneService.process(request);
    }
}
