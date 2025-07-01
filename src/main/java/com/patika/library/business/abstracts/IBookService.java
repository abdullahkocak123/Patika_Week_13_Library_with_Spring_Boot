package com.patika.library.business.abstracts;

import com.patika.library.enitites.Book;

import java.util.List;

public interface IBookService {

    Book save (Book book);

    Book update (Book book);

    Book get(long id);

    List<Book> getAll();

    boolean delete (long id);
}
