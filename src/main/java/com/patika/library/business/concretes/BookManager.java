package com.patika.library.business.concretes;

import com.patika.library.business.abstracts.IBookService;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.dao.BookRepo;
import com.patika.library.enitites.Author;
import com.patika.library.enitites.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements IBookService {

    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        //check if there is a data, if not throw message
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(long id) {
        return this.bookRepo.findById(id).orElseThrow(() ->  new NotFoundException(id));
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public boolean delete(long id) {
        //check if there is a data, if not throw message
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }
}
