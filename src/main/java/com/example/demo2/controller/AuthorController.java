package com.example.demo2.controller;

import com.example.demo2.model.Author;
import com.example.demo2.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "create-author";
    }



    @PostMapping("/authors")
    public String createAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/{authorId}/books")
    public String showAuthorBooks(@PathVariable Long authorId, Model model) {
        Author author = authorService.findById(authorId);
        model.addAttribute("author", author);
        model.addAttribute("books", author.getBooks());
        return "author-books";
    }


    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author_list";  // Имя шаблона для отображения списка авторов
    }



}

