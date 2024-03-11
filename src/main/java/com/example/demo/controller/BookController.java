package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private BookRepository bookRepository;

  public BookController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    kafkaTemplate.send("create-book", String.valueOf(book));
    return bookRepository.save(book);
  }

}

