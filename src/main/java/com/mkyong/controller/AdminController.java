package com.mkyong.controller;


import com.mkyong.controller.model.UserRoles;
import com.mkyong.dao.CityRepository;
import com.mkyong.model.BaseballCard;
import com.mkyong.model.City;
import com.mkyong.model.User;
import com.mkyong.service.AdminService;
import com.mkyong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String search(Model model) {
        return "login";

    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        Model model,
                        HttpServletRequest request) {
            adminService.prepareData();
        User user = userService.findUserByLoginAndPassword(login,password);
        if(user == null) {
            return "login";
        }
        else{
            if(user.getRole().equalsIgnoreCase(UserRoles.ADMIN.toString())) {
                List<User> users = adminService.getUsers();
                model.addAttribute("users",users);
                request.getSession().setAttribute("user",user);
                return "users";
            }
            else{
                return "index";
            }
        }

    }

    @RequestMapping(value = "/user/block", method = RequestMethod.GET)
    public String blockUser(@RequestParam String id, Model model,
                            HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return "login";
        }
        User user = userService.changeUserState(Integer.parseInt(id));
        model.addAttribute("user",user);

        return "editUser";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUseras(Model model,
                             HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return "login";
        }
        List<User> users = adminService.getUsers();
        model.addAttribute("users",users);
        return "users";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam String id, Model model,
                           HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return "login";
        }
        User user = userService.getUser(Integer.parseInt(id));
        model.addAttribute("user",user);
        return "editUser";
    }

    @RequestMapping(value = "/city/edit", method = RequestMethod.GET)
    public String editCity(@RequestParam String cityId, Model model,
                           HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return "login";
        }
        City city = adminService.getCity(Integer.parseInt(cityId));
        model.addAttribute("city",city);
        return "editCity";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequestMapping(value = "/city/update", method = RequestMethod.POST)
    public ModelAndView editCity(@ModelAttribute(value="city") City city, Model model,
                                 HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return new ModelAndView("redirect:/login");
        }
        adminService.prepareData();
        City alteredCity = cityRepository.findOne(city.getId());
        alteredCity.setName(city.getName());
        cityRepository.save(alteredCity);
        return new ModelAndView("redirect:/city/list");
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.POST)
    public ModelAndView addCity(@RequestParam String cityName, Model model,
                                HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return new ModelAndView("redirect:/login");
        }
        adminService.prepareData();
        List<City> cities = adminService.getCities("");
        City newCity = City.builder().name(cityName).build();
        cityRepository.save(newCity);
        model.addAttribute("foundCities",cities);
        return new ModelAndView("redirect:/city/list");
    }

    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public String showCities(@RequestParam(required = false, defaultValue = "") String cityName,
                             Model model,
                             HttpServletRequest request){
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser == null){
            return "login";
        }
        adminService.prepareData();
        List<City> cities = adminService.getCities(cityName);
        model.addAttribute("foundCities",cities);
        return "cities";
    }
}
