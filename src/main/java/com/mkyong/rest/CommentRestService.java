package com.mkyong.rest;

import com.mkyong.dao.CommentRepository;
import com.mkyong.dao.LocationRepository;
import com.mkyong.dao.UserRepository;
import com.mkyong.model.*;
import com.mkyong.service.AdminService;
import com.mkyong.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentRestService {

    @Autowired
    LocationService locationService;

    @Autowired
    AdminService adminService;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping(value = "rest/comment/get")
    public ResponseEntity<CommentDTO[]> getComments(@RequestBody Integer locationId){
        Location location = locationService.getLocation(locationId);
        List<CommentDTO> commentDTOS = location
                .getComments()
                .stream()
                .map(comment -> mapComment(comment))
                .collect(Collectors.toList());
        return new ResponseEntity(commentDTOS.toArray(new CommentDTO[0]), HttpStatus.ACCEPTED);
    }

    private CommentDTO mapComment(Comment comment) {
        CommentDTO commentDTO = CommentDTO
                .builder()
                .comment(comment.getComment())
                .rate(comment.getCommentRate())
                .commentDate(comment.getCommentDate())
                .userLogin(comment.getUser().getLogin())
                .longitude(comment.getLognitude())
                .latitude(comment.getLatitude())
                .commentPhoto(comment.getCommentPhoto())
                .build();

        return commentDTO;
    }

    @PostMapping(value = "rest/comment/add")
    public ResponseEntity getComments(@RequestBody CommentDTO commentDTO){
        User user = adminService.getUser(commentDTO.getUserId());
        Location location = adminService.getLocation(commentDTO.getLocationId());

        Comment comment = Comment.builder()
                .comment(commentDTO.getComment())
                .commentDate(commentDTO.getCommentDate())
                .location(location)
                .user(user)
                .lognitude(commentDTO.getLongitude())
                .latitude(commentDTO.getLatitude())
                .commentPhoto(commentDTO.getCommentPhoto())
                .commentRate(commentDTO.getRate())
                .build();

        commentRepository.save(comment);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
