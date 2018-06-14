package com.mkyong.dao;

import com.mkyong.model.BaseballCard;
import com.mkyong.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByLoginAndPassword(String login, String password);

    User findUserById(long id);
}
