package com.patika.library.api;

import com.patika.library.business.abstracts.IBookBorrowingService;
import com.patika.library.business.abstracts.IBookService;
import com.patika.library.core.config.modelMapper.IModelMapperService;
import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;
import com.patika.library.core.utils.ResultHelper;
import com.patika.library.dto.request.book_borrowing.BookBorrowingSaveRequest;
import com.patika.library.dto.request.book_borrowing.BookBorrowingUpdateRequest;
import com.patika.library.dto.response.book_borrowing.BookBorrowingResponse;
import com.patika.library.enitites.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book-borrowings")
public class BookBorrowingController {

    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper, IBookService bookService) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing saveBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);

        Book book = this.bookService.get(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);

        this.bookBorrowingService.save(saveBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.createdData(bookBorrowingResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);

        Book book = this.bookService.get(bookBorrowingUpdateRequest.getBookId());
        updateBookBorrowing.setBook(book);

        this.bookBorrowingService.update(updateBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.createdData(bookBorrowingResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") long id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.successData(bookBorrowingResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<BookBorrowingResponse>> getAll() {
        List<BookBorrowing> bookBorrowingList = this.bookBorrowingService.getAll();
        List<BookBorrowingResponse> bookBorrowingResponseList = bookBorrowingList.stream()
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class))
                .toList();

        return ResultHelper.successData(bookBorrowingResponseList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") long id){
        this.bookBorrowingService.delete(id);
        return ResultHelper.success();
    }

}
