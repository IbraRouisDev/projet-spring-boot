package com.example.projetspringboot.repositories;

import com.example.projetspringboot.entities.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {
    Library findById(@Param("id") long id);

    List<Library> findByCityName(@Param("name") String name);

    List<Library> findByNameContaining(@Param("name") String name);

    List<Library> findByBooksName(@Param("books_name") String books_name);

    Library findByLocation(@Param("location") String location);

    Library findByName(@Param("name") String name);
}
