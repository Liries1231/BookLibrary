package com.example.demo2.service;

import com.example.demo2.model.Author;
import com.example.demo2.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRep;

    public List<Author> findAll() {
        return authorRep.findAll();
    }

    public void save(Author author) {
        authorRep.save(author);
    }
}



