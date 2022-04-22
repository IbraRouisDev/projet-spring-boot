package com.example.projetspringboot.formData;

public class BookToLibraryFormData {
    private long library_id;
    private long book_id;

    public long getBook_id() {
        return book_id;
    }

    public long getLibrary_id() {
        return library_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public void setLibrary_id(long library_id) {
        this.library_id = library_id;
    }
}
