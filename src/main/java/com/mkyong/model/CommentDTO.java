package com.mkyong.model;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String comment;
    private Integer rate;
    private String userLogin;
    private Date commentDate;
    private Integer userId;
    private Integer locationId;
    private byte[] commentPhoto;
    private Double longitude;
    private Double latitude;

}

