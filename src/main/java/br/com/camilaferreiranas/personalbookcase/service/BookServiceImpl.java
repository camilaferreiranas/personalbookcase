package br.com.camilaferreiranas.personalbookcase.service;

import br.com.camilaferreiranas.personalbookcase.model.Book;
import br.com.camilaferreiranas.personalbookcase.model.BookResponseDTO;
import br.com.camilaferreiranas.personalbookcase.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;



    @Override
    public BookResponseDTO save(BookResponseDTO book) {
        Book bookEntity = new Book();
        bookEntity.setTitle(book.title());
        bookEntity.setAuthor(book.author());
        bookEntity.setGenre(book.genre());
        bookEntity.setReview(book.review());
        var entity = bookRepository.save(bookEntity);
        return BookResponseDTO.fromEntity(entity);
    }

    @Override
    public List<BookResponseDTO> saveList(List<BookResponseDTO> books) {
        return books.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> new BookResponseDTO(book.getTitle(),
                        book.getAuthor(), book.getGenre(), book.getReview()))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO findById(Long id) {
        return bookRepository.findById(id).map(BookResponseDTO::fromEntity).orElseThrow();
    }

    @Override
    public BookResponseDTO findByTitle(String title) {
        return bookRepository.findByTitle(title).map(BookResponseDTO::fromEntity).orElseThrow();
    }
}
