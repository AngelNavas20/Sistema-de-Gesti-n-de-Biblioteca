package com.api.school.controller;

import com.api.school.entity.Book;
import com.api.school.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookDocumentService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookDocumentService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        Book bookDto = bookDocumentService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> createBook(@RequestBody Book bookDto) {
        Book saved = bookDocumentService.saveBook(bookDto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") String id) {
        bookDocumentService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
