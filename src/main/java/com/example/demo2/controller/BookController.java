package com.example.demo2.controller;

import com.example.demo2.model.Book;
import com.example.demo2.service.AuthorService;
import com.example.demo2.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }




    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "create-book";
    }
    @GetMapping("/books")
    public ModelAndView listBooks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        Page<Book> bookPage = bookService.getAllBooks(page, size);
        ModelAndView modelAndView = new ModelAndView("book_list");
        modelAndView.addObject("books", bookPage.getContent());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", bookPage.getTotalPages());
        return modelAndView;
    }
    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book_details";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteById(id);
        return "redirect:/books";
    }



    @PostMapping("/books")
    public String createBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
}
