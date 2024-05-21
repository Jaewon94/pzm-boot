package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    // GET : http://localhost:8081/api/books
    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllList(), HttpStatus.OK);
    }

    // POST : http://localhost:8081/api/books
    @PostMapping("/books")
    public ResponseEntity<?> register(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.register(book), HttpStatus.CREATED);
    }

    // GET : http://localhost:8081/api/books/{id}
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT : http://localhost:8081/api/books/{id}
    @PutMapping("/books/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book reqBook) {
        try {
            Book book = bookService.update(id, reqBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE : http://localhost:8081/api/books/{id}
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            bookService.delete(id);
            return new ResponseEntity<>("Delete Book with id :" +id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
