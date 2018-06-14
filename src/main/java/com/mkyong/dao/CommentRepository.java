package com.mkyong.dao;

import com.mkyong.model.Comment;
import com.mkyong.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment,Long>{
}
