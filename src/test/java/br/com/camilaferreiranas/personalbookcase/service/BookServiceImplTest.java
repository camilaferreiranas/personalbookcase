package br.com.camilaferreiranas.personalbookcase.service;

import br.com.camilaferreiranas.personalbookcase.model.Book;
import br.com.camilaferreiranas.personalbookcase.model.BookResponseDTO;
import br.com.camilaferreiranas.personalbookcase.model.GenreEnum;
import br.com.camilaferreiranas.personalbookcase.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookResponseDTO bookResponseDTO;


    @BeforeEach
    void setUp() {
        book = new Book();
        book.setTitle("Spring Boot Guide");
        book.setAuthor("John Doe");
        book.setGenre(GenreEnum.TECHNICAL);
        book.setReview(4);

        bookResponseDTO = new BookResponseDTO("Spring Boot Guide", "John Doe", GenreEnum.TECHNICAL, 4);
    }

    @Test
    void testSaveBook() {
        when(bookRepository.save(book)).thenReturn(book);
        BookResponseDTO savedBookResponseDTO = bookService.save(bookResponseDTO);
        assertNotNull(savedBookResponseDTO);
        assertEquals(book.getTitle(), savedBookResponseDTO.title());
        assertEquals(book.getAuthor(), savedBookResponseDTO.author());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testSaveList() {
        List<BookResponseDTO> booksToSave = List.of(bookResponseDTO);
        when(bookRepository.save(book)).thenReturn(book);

        List<BookResponseDTO> savedBooks = bookService.saveList(booksToSave);

        assertNotNull(savedBooks);
        assertEquals(1, savedBooks.size());
        assertEquals(book.getTitle(), savedBooks.get(0).title());

        verify(bookRepository, times(1)).save(any(Book.class));
    }


    @Test
    void testFindAll() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookResponseDTO> books = bookService.findAll();

        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(book.getTitle(), books.get(0).title());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDTO foundBook = bookService.findById(1L);

        assertNotNull(foundBook);
        assertEquals(book.getTitle(), foundBook.title());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.findById(2L));

        verify(bookRepository, times(1)).findById(2L);
    }

    @Test
    void testFindByTitle() {
        when(bookRepository.findByTitle("Spring Boot Guide")).thenReturn(Optional.of(book));

        BookResponseDTO foundBook = bookService.findByTitle("Spring Boot Guide");

        assertNotNull(foundBook);
        assertEquals(book.getTitle(), foundBook.title());

        verify(bookRepository, times(1)).findByTitle("Spring Boot Guide");
    }

    @Test
    void testFindByTitle_NotFound() {
        when(bookRepository.findByTitle("Unknown Book")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.findByTitle("Unknown Book"));

        verify(bookRepository, times(1)).findByTitle("Unknown Book");
    }
}
