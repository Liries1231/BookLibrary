package com.example.demo2.controller;

import com.example.demo2.model.Book;
import com.example.demo2.service.AuthorService;
import com.example.demo2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // Отображение формы для создания книги
    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());  // Добавьте всех авторов в модель
        return "create-book";  // Имя шаблона для создания книги
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book_list";  // Имя шаблона для отображения списка книг
    }

    // Обработка отправки формы создания книги
    @PostMapping("/books")
    public String createBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";  // Перенаправление на список книг после создания
    }
}
