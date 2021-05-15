package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BookExistsException;
import org.loose.fis.sre.exceptions.UncompletedFieldsException;
import org.loose.fis.sre.exceptions.UncompletedTitleException;
import org.loose.fis.sre.exceptions.WrongFieldsException;
import org.loose.fis.sre.model.Book;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class BookService {
    public static ObjectRepository<Book> bookRepository;

    public static void initDatabase() {

        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("books.db").toFile())
                .openOrCreate("biblioteca", "biblioteca");

        bookRepository = database.getRepository(Book.class);
    }

    public static void addBook(String titlu, String autor, String limba, String path, String description) throws  UncompletedFieldsException,BookExistsException {
        AllFieldsCompleted(titlu, autor, path, description);
        checkBookDoesNotAlreadyExists(titlu, autor, limba);
        Book b = new Book(titlu, autor, limba, path, description);
        UUID u = b.getBook_id();
        while (!checkIDisUnic(u)) {
            u = b.rando();
            checkIDisUnic(u);
        }
        bookRepository.insert(b);
    }

    public static void deleteBook(String titlu) throws UncompletedFieldsException, UncompletedTitleException
    {
        int sw=0;
        Book aux=new Book();
        if(titlu=="")
            throw new UncompletedTitleException("Complete the title box");
        for(Book book : bookRepository.find())
        {
            if(titlu.equals(book.getTitlu())) {
                aux = book;
                sw=1;
            }
        }
        if(sw==0) {
            throw new UncompletedFieldsException("Complete the empty fields");
        }
        bookRepository.remove(aux);
    }

    public static void checkBookDoesNotAlreadyExists(String titlu, String autor, String limba) throws BookExistsException {
        Cursor<Book> cursor = bookRepository.find();
        for (Book book : cursor) {
            if (titlu.equals(book.getTitlu())) {
                if (autor.equals(book.getAutor())) {
                    if (limba.equals(book.getLimba())) {
                        throw new BookExistsException("Cartea există deja în bibliotecă!");
                    }
                }
            }
        }
    }

    public static boolean checkIDisUnic(UUID u) {
        Cursor<Book> cursor = bookRepository.find();
        for (Book book : cursor) {
            if (u.equals(book.getBook_id())) {
                return false;
            }
        }
        return true;
    }

    public static boolean AllFieldsCompleted(String titlu, String autor,String categorie,String descr) throws UncompletedFieldsException {
        Pattern pattern = Pattern.compile("[\\S+]");
        Matcher matcher1 = pattern.matcher(titlu);
        Matcher matcher2 = pattern.matcher(autor);
        Matcher matcher3 = pattern.matcher(categorie);
        Matcher matcher4 = pattern.matcher(descr);
        boolean matchFound1 = matcher1.find();
        boolean matchFound2 = matcher2.find();
        boolean matchFound4 = matcher3.find();
        boolean matchFound5 = matcher4.find();
        if (!matchFound1) throw new UncompletedFieldsException("You need to complete all the fields!");
        if (!matchFound2) throw new UncompletedFieldsException("You need to complete all the fields!");
        if (!matchFound4) throw new UncompletedFieldsException("You need to complete all the fields!");
        if (!matchFound5) throw new UncompletedFieldsException("You need to complete all the fields!");
        return true;


    }
    public static ArrayList<Book> getLast() {
        Cursor<Book> cursor = bookRepository.find();
        ArrayList<Book> a=new ArrayList<>();
        int cnt = cursor.totalCount();
        int cnt2=0;
        for (Book book : cursor) {
            cnt2++;
            if(cnt2==cnt-2)
            {
                a.add(book);
            }
            if(cnt2==cnt-1)
            {
                a.add(book);
            }
            if(cnt2==cnt)
            {
                a.add(book);
            }
        }
        return a;
    }
}