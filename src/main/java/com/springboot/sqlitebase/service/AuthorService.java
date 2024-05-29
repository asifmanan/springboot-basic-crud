package com.springboot.sqlitebase.service;

import com.springboot.sqlitebase.model.Author;
import com.springboot.sqlitebase.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Integer id) {
        Optional<Author> result = authorRepository.findById(id);

        Author author = null;

        if (result.isPresent()) {
            author = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return author;
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}
