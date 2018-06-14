package com.mkyong.rest;

import com.mkyong.dao.CityRepository;
import com.mkyong.dao.UserRepository;
import com.mkyong.model.City;
import com.mkyong.model.Location;
import com.mkyong.model.LocationDTO;
import com.mkyong.model.User;
import com.mkyong.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationRestService {

    @Autowired
    private LocationService locationService;


    @PostMapping(value = "rest/location/list")
    public ResponseEntity<LocationDTO[]> getCities(@RequestBody Integer cityId){
        List<Location> locations = locationService.getLocations(Long.valueOf(cityId));
        List<LocationDTO> locationDTOS = locations
                .stream()
                .map(location->{
                    LocationDTO locationDTO = mapLocation(location);
                    return locationDTO;
                })
                .collect(Collectors.toList());
        return new ResponseEntity(locationDTOS.toArray(new LocationDTO[0]), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "rest/location/get")
    public ResponseEntity<LocationDTO> getLocation(@RequestBody Integer locationId){
        Location location = locationService.getLocation(locationId);
        LocationDTO locationDTO = mapLocation(location);
        return new ResponseEntity(locationDTO, HttpStatus.ACCEPTED);
    }

    private LocationDTO mapLocation(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setPhoto(location.getPhoto());
        locationDTO.setId(Long.valueOf(location.getId()));
        locationDTO.setDescription(location.getDescription());
        locationDTO.setName(location.getName());
        locationDTO.setLognitude(location.getLognitude());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setCityId(location.getCity().getId());
        locationDTO.setUserId(location.getUser().getId());
        return locationDTO;
    }

    @PostMapping(value = "rest/location/save")
    public ResponseEntity<LocationDTO> saveLocation(@RequestBody LocationDTO locationDTO){

        locationService.saveLocation(locationDTO);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }



}
