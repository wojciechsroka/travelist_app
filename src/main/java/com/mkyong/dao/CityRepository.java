package com.mkyong.dao;

import com.mkyong.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City,Long> {

    @Query(" FROM City c WHERE c.name LIKE CONCAT('%',:cityName,'%')")
    List<City> nameContaining(String cityName);
}
