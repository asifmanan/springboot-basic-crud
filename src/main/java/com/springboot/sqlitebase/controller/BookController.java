package com.springboot.sqlitebase.controller;

import com.springboot.sqlitebase.model.Book;
import com.springboot.sqlitebase.service.AuthorService;
import com.springboot.sqlitebase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/listBook";
    }

    @GetMapping("/add")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "books/editBook";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books/list";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam("bookId") Integer id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "books/editBook";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") Integer id) {
        bookService.deleteById(id);
        return "redirect:/books/list";
    }
}
