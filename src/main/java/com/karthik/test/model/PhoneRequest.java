package com.karthik.test.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PhoneRequest {
    @NotNull
    private String phoneNumber;

    private int pageNumber=1;
    private int pageSize =100;
}
