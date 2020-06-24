package com.karthik.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PhoneResponse {

    private List<PhoneNumber> phoneNumbers;
    private int total;
}
