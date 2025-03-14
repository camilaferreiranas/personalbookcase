package br.com.camilaferreiranas.personalbookcase.controllers;

import br.com.camilaferreiranas.personalbookcase.model.BookResponseDTO;
import br.com.camilaferreiranas.personalbookcase.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponseDTO> save(@RequestBody BookResponseDTO book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping(path = "/saveList")
    public ResponseEntity<List<BookResponseDTO>> saveList(@RequestBody List<BookResponseDTO> books) {
        return ResponseEntity.ok(bookService.saveList(books));
    }

    @GetMapping(path = "title/{title}")
    public ResponseEntity<BookResponseDTO> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
}
