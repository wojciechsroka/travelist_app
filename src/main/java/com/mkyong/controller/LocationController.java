package com.mkyong.controller;

import com.mkyong.model.City;
import com.mkyong.model.Location;
import com.mkyong.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/location/accept", method = RequestMethod.GET)
    public ModelAndView accept(@RequestParam(value="locationId") String locationId, Model model){
        Location location = locationService.getLocationsById(Integer.parseInt(locationId));
        location.setAccepted(Boolean.TRUE);
        locationService.updateLocation(location);
        return new ModelAndView("redirect:/location/list");
    }

    @RequestMapping(value = "/location/decline", method = RequestMethod.GET)
    public ModelAndView decline(@RequestParam(value="locationId") String locationId, Model model){
        Location location = locationService.getLocationsById(Integer.parseInt(locationId));
        location.setAccepted(Boolean.FALSE);
        locationService.updateLocation(location);
        return new ModelAndView("redirect:/location/list");
    }


    @RequestMapping(value = "/location/list", method = RequestMethod.GET)
    public String showLocations(Model model){
        List<Location> locations = locationService.getLocations();
        model.addAttribute("locations",locations);
        return "locations";
    }
}
