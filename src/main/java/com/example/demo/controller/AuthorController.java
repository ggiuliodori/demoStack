package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

  @Autowired
  private AuthorRepository authorRepository;

  @GetMapping
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @PostMapping
  public Author createBook(@RequestBody Author author) {
    return authorRepository.save(author);
  }

}

