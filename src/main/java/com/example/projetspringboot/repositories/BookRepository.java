package com.example.projetspringboot.repositories;

import com.example.projetspringboot.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findById(@Param("id") long id);
    Book findByName(@Param("name") String name);
    List<Book> findByNameLike(@Param("name") String name);
}
