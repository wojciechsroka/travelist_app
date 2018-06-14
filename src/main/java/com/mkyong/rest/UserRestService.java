package com.mkyong.rest;

import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import com.mkyong.model.UserStatsDTO;
import com.mkyong.service.AdminService;
import com.mkyong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    @PostMapping(value = "rest/user/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTOreq){
        adminService.prepareData();
        User user = userService.findUserByLoginAndPassword(userDTOreq.getLogin(),userDTOreq.getPassword());
        UserDTO userDTO = new UserDTO();
        if(user != null) {
            userDTO.setLogin(user.getLogin());
            userDTO.setActive(user.getIsActive());
            userDTO.setUserId(user.getId().intValue());
            userDTO.setEmail(user.getEmail());
        }

        return new ResponseEntity(userDTO, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "rest/user/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTOreq){
        userService.updateUser(userDTOreq);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "rest/user/stats")
    public ResponseEntity<UserStatsDTO> getUserStats(){
        UserStatsDTO userStatsDTO = userService.getUserStats();

        return new ResponseEntity<>(userStatsDTO,HttpStatus.ACCEPTED);
    }

}
