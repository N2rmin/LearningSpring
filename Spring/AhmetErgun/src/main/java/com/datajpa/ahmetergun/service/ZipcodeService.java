package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.requestDto.ZipcodeRequestDto;
import com.datajpa.ahmetergun.model.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipcodeService {
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);

    public List<Zipcode> getZipcodes();
    public Zipcode getZipcode(Long zipcodeId);
    public Zipcode deleteZipcode (Long zipcodeId);
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId);
    public Zipcode removeCityFromZipcode(Long zipcodeId);


}
