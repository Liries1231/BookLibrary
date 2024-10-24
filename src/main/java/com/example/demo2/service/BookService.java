package com.example.demo2.service;

import com.example.demo2.model.Book;
import com.example.demo2.repository.BookRep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRep bookRep;




    public Page<Book> getAllBooks(Pageable pageable){
        return bookRep.findAll(pageable);
    }

    public Book getBookById(Long id){
        return bookRep.findById(id).orElseThrow(()
                -> new RuntimeException("No book" ));
    }
    public void save(Book book) {
        bookRep.save(book);
    }

    public List<Book> findAll(){
        return bookRep.findAll();
    }



}
