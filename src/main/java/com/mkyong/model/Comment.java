package com.mkyong.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"location","user"})
@ToString(exclude={"location","user"})
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name ="comment")
    private String comment;

    @Column(name ="comment_rate")
    private Integer commentRate;

    @Column(name ="comment_date")
    private Date commentDate;

    @Column(name ="photo",length = 5 * 1024 * 1024)
    private byte[] commentPhoto;

    @Column(name ="log")
    private Double lognitude;

    @Column(name ="lat")
    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
