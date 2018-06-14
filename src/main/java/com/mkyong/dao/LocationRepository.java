package com.mkyong.dao;

import com.mkyong.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Integer> {
    List<Location> findByCity_Id(Long cityId);
}
