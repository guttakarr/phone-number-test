package com.karthik.test.service;

import com.karthik.test.model.PhoneNumber;
import com.karthik.test.model.PhoneRequest;
import com.karthik.test.model.PhoneResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    public PhoneResponse process(PhoneRequest request){
        List<String> numbers = getNumbers(request.getPhoneNumber());
        int start = (request.getPageNumber() - 1 ) * request.getPageSize();
        int end = start + request.getPageSize();
        if(end >  numbers.size()){
            end = numbers.size();
        }
        List<String> list = numbers.subList(start, end);
        return new PhoneResponse(list.stream().map(PhoneNumber::new).collect(Collectors.toList()), numbers.size());
    }


    private List<String> getNumbers(String phoneNumber){
        Set<String> results = new LinkedHashSet<>();
        for(int i =0; i<phoneNumber.length();i++){
            List<String> keyPads = keyPad(phoneNumber.charAt(i));
            String first = phoneNumber.substring(0,i);
            String next = phoneNumber.substring(i+1);
            for (String e : keyPads){
                results.add(first+e+next);
            }
        }
        ArrayList<String> list = new ArrayList<>(results);
        Collections.sort(list);
        return list;
    }

    private List<String> keyPad(char number){
        switch (number){
            case '1': return List.of("1");
            case '2': return List.of("2","A","B","C");
            case '3': return List.of("3","D","E","F");
            case '4': return List.of("4","G","H","I");
            case '5': return List.of("5","K","L");
            case '6': return List.of("6","M","N","O");
            case '7': return List.of("7","P","Q","R","S");
            case '8': return List.of("8","T","U","V");
            case '9': return List.of("9","W","X","Y","Z");
            case '0': return List.of("0","+");
        }
        return Collections.emptyList();
    }

}
