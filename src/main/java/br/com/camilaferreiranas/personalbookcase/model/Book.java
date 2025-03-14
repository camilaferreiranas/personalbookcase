package br.com.camilaferreiranas.personalbookcase.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    private int review;


}
