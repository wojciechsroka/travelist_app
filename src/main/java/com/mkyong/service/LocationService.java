package com.mkyong.service;

import com.mkyong.dao.CityRepository;
import com.mkyong.dao.LocationRepository;
import com.mkyong.dao.UserRepository;
import com.mkyong.model.City;
import com.mkyong.model.Location;
import com.mkyong.model.LocationDTO;
import com.mkyong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Location> getLocations(Long cityId){
        List<Location> locations = locationRepository.findByCity_Id(cityId);
        return locations;
    }

    public Location getLocation(Integer locationId) {
        return locationRepository.findOne(locationId);
    }

    public void saveLocation(LocationDTO locationDTO) {
        User user = userRepository.findUserById(locationDTO.getUserId());
        City city = cityRepository.findOne(locationDTO.getCityId());


        Location location = Location.builder()
                .description(locationDTO.getDescription())
                .name(locationDTO.getName())
                .user(user)
                .city(city)
                .photo(locationDTO.getPhoto())
                .latitude(locationDTO.getLatitude())
                .lognitude(locationDTO.getLognitude())
                .build();

        locationRepository.save(location);
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().iterator().forEachRemaining(locations::add);
        return locations;
    }

    public Location getLocationsById(int locationId) {
        return locationRepository.findOne(locationId);
    }

    public void updateLocation(Location location) {
        locationRepository.save(location);
    }
}
