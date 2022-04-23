package com.example.projetspringboot.repositories;

import com.example.projetspringboot.entities.City;
import com.example.projetspringboot.entities.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    City findById(@Param("id") long id);

    City findByName(@Param("name") String name);

    List<City> findByNameContainingIgnoreCase(@Param("name") String name);
}
