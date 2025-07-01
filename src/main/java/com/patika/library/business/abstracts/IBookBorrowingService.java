package com.patika.library.business.abstracts;

import com.patika.library.enitites.BookBorrowing;

import java.util.List;

public interface IBookBorrowingService {

    BookBorrowing save (BookBorrowing bookBorrowing);

    BookBorrowing update (BookBorrowing bookBorrowing);

    BookBorrowing get(long id);

    List<BookBorrowing> getAll();

    boolean delete (long id);
}
