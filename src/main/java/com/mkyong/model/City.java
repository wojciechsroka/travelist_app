package com.mkyong.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString(exclude = "locations")
@AllArgsConstructor
public class City {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private Set<Location> locations;

}
