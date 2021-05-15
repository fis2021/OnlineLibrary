package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;
import java.util.UUID;

public class Book {
    @Id
    private UUID book_id;
    private String titlu;
    private String autor;
    private String limba;
    private String photo_path;
    private String description;

    public Book( String titlu, String autor, String limba, String photo_path, String description) {
        this.book_id =UUID.randomUUID();
        this.titlu = titlu;
        this.autor = autor;
        this.limba = limba;
        this.photo_path = photo_path;
        this.description = description;
    }
    public Book() {

    }
    public UUID rando()
    {
        this.book_id =UUID.randomUUID();
        return this.book_id;

    }

    public UUID getBook_id() {
        return book_id;
    }

    public String getTitlu() {
        return titlu;
    }

    /*public void setTitlu(String titlu) {
        this.titlu = titlu;
    }*/

    public String getAutor() {
        return autor;
    }

    /*public void setAutor(String autor) {
        this.autor = autor;
    }*/

    public String getLimba() {
        return limba;
    }

    /*public void setLimba(String limba) {
        this.limba = limba;
    }*/

    public String getPhoto_path() {
        return photo_path;
    }

    /*public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }*/

    public String getDescription() {
        return description;
    }

    /*public void setDescription(String description) {
        this.description = description;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBook_id().equals(book.getBook_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook_id());
    }
}