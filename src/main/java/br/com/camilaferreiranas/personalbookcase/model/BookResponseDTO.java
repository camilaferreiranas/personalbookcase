package br.com.camilaferreiranas.personalbookcase.model;

public record BookResponseDTO(String title, String author, GenreEnum genre, int review) {

    public static BookResponseDTO fromEntity(Book book) {
        return new BookResponseDTO(book.getTitle(), book.getAuthor(), book.getGenre(), book.getReview());
    }
}
