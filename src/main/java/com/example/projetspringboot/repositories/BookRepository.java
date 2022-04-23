package com.example.projetspringboot.repositories;

import com.example.projetspringboot.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findById(@Param("id") long id);

    Book findByName(@Param("name") String name);

    List<Book> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author, Pageable pageable);

    List<Book> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author);
}
