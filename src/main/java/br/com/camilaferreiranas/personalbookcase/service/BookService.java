package br.com.camilaferreiranas.personalbookcase.service;

import br.com.camilaferreiranas.personalbookcase.model.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookResponseDTO save(BookResponseDTO book);
    List<BookResponseDTO> saveList(List<BookResponseDTO> books);
    List<BookResponseDTO> findAll();
    BookResponseDTO findById(Long id);
    BookResponseDTO findByTitle(String title);
}
