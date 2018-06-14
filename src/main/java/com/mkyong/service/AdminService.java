package com.mkyong.service;

import com.mkyong.controller.model.UserRoles;
import com.mkyong.dao.CityRepository;
import com.mkyong.dao.CommentRepository;
import com.mkyong.dao.LocationRepository;
import com.mkyong.dao.UserRepository;
import com.mkyong.model.City;
import com.mkyong.model.Comment;
import com.mkyong.model.Location;
import com.mkyong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public void prepareData(){

        if(getUsers().size() > 0){
            return;
        }

        User user = User.builder().login("admin")
                .password("secret")
                .isActive(true)
                .role(UserRoles.ADMIN.toString()).build();

        userRepository.save(user);

        user = User.builder()
                .login("a")
                .password("a")
                .email("a@a.pl")
                .isActive(true)
                .role(UserRoles.USER.toString())
                .build();
        userRepository.save(user);

        City city = City.builder()
                .name("Krakow")
                .build();
        city = cityRepository.save(city);


        Location location = Location.builder()
                .name("Loc 1")
                .description("Desc 1")
                .user(user)
                .city(city)
                .build();
        location = locationRepository.save(location);

        Location location2 = Location.builder()
                .name("Loc 2")
                .description("Desc 2")
                .user(user)
                .city(city)
                .build();
        location2 = locationRepository.save(location2);


        Set<Location> locations = new HashSet();
        locations.add(location);
        locations.add(location2);
        user.setLocations(locations);
        userRepository.save(user);

        Comment com1 = Comment
                .builder()
                .comment("Kom1")
                .location(location)
                .user(user)
                .commentRate(3)
                .commentDate(new Date())
                .build();

        commentRepository.save(com1);

        com1 = Comment
                .builder()
                .comment("Kom2")
                .location(location)
                .user(user)
                .commentRate(5)
                .commentDate(new Date())
                .build();

        commentRepository.save(com1);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    public City getCity(long id) {
        return cityRepository.findOne(id);
    }

    public List<City> getCities(String cityName) {
        if(cityName != null && cityName.length() > 0) {
            return cityRepository.nameContaining(cityName);
        }
        else{
            List<City> cities = new ArrayList<>();
            cityRepository.findAll().iterator().forEachRemaining(cities::add);
            return cities;
        }
    }

    public User getUser(Integer userId) {
        return userRepository.findUserById(userId);
    }

    public Location getLocation(Integer locationId) {
        return locationRepository.findOne(locationId);
    }
}
