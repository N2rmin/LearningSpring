package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.requestDto.CityRequestDto;
import com.datajpa.ahmetergun.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();
    public City getCity(Long cityId);
    public City deleteCity( Long cityId);
    public City editCity(Long cityId, CityRequestDto cityRequestDto);
}
