package com.mkyong.model;


import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude="locations")
@ToString(exclude = "locations")
public class User {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "email")
    private String email;
    @Column(name = "isActive")
    private Boolean isActive;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Location> locations;

}
