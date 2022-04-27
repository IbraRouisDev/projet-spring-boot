package com.example.projetspringboot.repositories;

import com.example.projetspringboot.entities.Book;
import com.example.projetspringboot.entities.Library;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findById(@Param("id") long id);

    Book findByName(@Param("name") String name);

    List<Book> findByNameContainingIgnoreCase(@Param("name") String name, @ParameterObject Pageable pageable);

    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author, @ParameterObject Pageable pageable);

    List<Book> findByNameContaining(@Param("name") String name);

    List<Book> findByAuthorContaining(@Param("author") String author);
}
