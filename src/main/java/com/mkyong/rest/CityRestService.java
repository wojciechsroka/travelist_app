package com.mkyong.rest;

import com.mkyong.dao.CityRepository;
import com.mkyong.model.City;
import com.mkyong.model.CityDTO;
import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import com.mkyong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityRestService {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "rest/city/list")
    public ResponseEntity<CityDTO[]> getCities(){
        List<City> cities = adminService.getCities("");
        List<CityDTO> cityDTOS = cities
                .stream()
                .map(city->{
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setId(city.getId());
                    cityDTO.setDescription(city.getDescription());
                    cityDTO.setName(city.getName());
                    return cityDTO;
                    })
                .collect(Collectors.toList());
        return new ResponseEntity(cityDTOS.toArray(new CityDTO[0]), HttpStatus.ACCEPTED);
    }
}
