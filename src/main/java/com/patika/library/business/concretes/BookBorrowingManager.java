package com.patika.library.business.concretes;

import com.patika.library.business.abstracts.IBookBorrowingService;
import com.patika.library.business.abstracts.IBookService;
import com.patika.library.core.exception.NotEnoughStockException;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.dao.BookBorrowingRepo;
import com.patika.library.dao.BookRepo;
import com.patika.library.enitites.Author;
import com.patika.library.enitites.Book;
import com.patika.library.enitites.BookBorrowing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {

    private final BookBorrowingRepo bookBorrowingRepo;
    private final IBookService bookService;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo, IBookService bookService) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookService = bookService;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {

        Book book = this.bookService.get(bookBorrowing.getBook().getId());
        int stock = book.getStock();

        if (stock<=0){
            throw new NotEnoughStockException();
        }

        book.setStock(stock-1);
        this.bookService.update(book);

        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        //check if there is a data, if not throw message
        this.get(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(long id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() ->  new NotFoundException(id));
    }

    @Override
    public List<BookBorrowing> getAll() {
        return this.bookBorrowingRepo.findAll();
    }

    @Override
    public boolean delete(long id) {
        //check if there is a data, if not throw message
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }
}
