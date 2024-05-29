package com.springboot.sqlitebase.controller;

import com.springboot.sqlitebase.model.Author;
import com.springboot.sqlitebase.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/listAuthor";
    }

    @GetMapping("/add")
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/editAuthor";
    }

    @PostMapping("/add")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.save(author);
        return "redirect:/authors/list";
    }

    @GetMapping("/update")
    public String updateAuthor(@RequestParam("authorId") Integer id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "authors/editAuthor";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("authorId") Integer id) {
        authorService.deleteById(id);
        return "redirect:/authors/list";
    }
}
