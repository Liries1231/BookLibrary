package com.example.demo2.repository;

import com.example.demo2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface BookRep extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
}
