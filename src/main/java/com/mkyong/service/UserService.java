package com.mkyong.service;

import com.mkyong.dao.CommentRepository;
import com.mkyong.dao.UserRepository;
import com.mkyong.model.Comment;
import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import com.mkyong.model.UserStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public User findUserByLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login,password);
    }

    public void insertTestUser() {
        User user = User.builder().login("admin")
                .password("secret")
                .role("A").build();

        userRepository.save(user);
        user = User.builder().login("user1")
                .password("secret")
                .role("U").build();

        userRepository.save(user);

    }

    public User changeUserState(int id) {
        User user = userRepository.findUserById((long) id);
        if(user != null){
            user.setIsActive(!user.getIsActive());
            userRepository.save(user);
        }
        return user;
    }

    public User getUser(long id){
        return userRepository.findUserById(id);
    }

    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findUserById(userDTO.getUserId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    public UserStatsDTO getUserStats() {
        List<User> allUsers = new ArrayList<>();
        List<Comment> allComments = new ArrayList<>();

        commentRepository.findAll().iterator().forEachRemaining(allComments::add);
        userRepository.findAll().iterator().forEachRemaining(allUsers::add);

        allUsers = allUsers.stream().filter(user -> user.getLogin() != null).collect(Collectors.toList());

        Map<String,Integer> userstats = new HashMap<>();
                allUsers.stream()
                .forEach(u-> userstats.put(u.getLogin(),0));

        allComments
                .stream()
                .forEach(c ->
                        fillData(userstats, c)
                );

        UserStatsDTO userStatsDTO = new UserStatsDTO();
        userstats.putAll(userstats);
        userStatsDTO.setStats(userstats);
        return userStatsDTO;
    }

    private Integer fillData(Map<String, Integer> userstats, Comment c) {
        Integer count = userstats.get(c.getUser().getLogin())+1;
        return userstats.put(c.getUser().getLogin(),count);
    }
}
