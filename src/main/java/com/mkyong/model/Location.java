package com.mkyong.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "location")
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"city","user,comments"})
@ToString(exclude={"city","user,comments"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="log")
    private Double lognitude;

    @Column(name ="lat")
    private Double latitude;

    @Column(name ="photo",length = 5 * 1024 * 1024)
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private Set<Comment> comments;

    @Column(name ="accepted")
    private Boolean accepted;
}
