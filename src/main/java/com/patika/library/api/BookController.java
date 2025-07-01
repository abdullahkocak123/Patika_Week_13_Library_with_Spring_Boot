package com.patika.library.api;

import com.patika.library.business.abstracts.IAuthorService;
import com.patika.library.business.abstracts.IBookService;
import com.patika.library.business.abstracts.ICategoryService;
import com.patika.library.business.abstracts.IPublisherService;
import com.patika.library.core.config.modelMapper.IModelMapperService;
import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;
import com.patika.library.core.utils.ResultHelper;
import com.patika.library.dto.request.book.BookSaveRequest;
import com.patika.library.dto.request.book.BookUpdateRequest;
import com.patika.library.dto.response.book.BookResponse;
import com.patika.library.enitites.Author;
import com.patika.library.enitites.Book;
import com.patika.library.enitites.Category;
import com.patika.library.enitites.Publisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;
    private final ICategoryService categoryService;

    public BookController(IBookService bookService, IModelMapperService modelMapper, IAuthorService authorService, IPublisherService publisherService, ICategoryService categoryService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        if (bookSaveRequest.getCategoryIdList() != null && !bookSaveRequest.getCategoryIdList().isEmpty()) {
            List<Category> categoryList = bookSaveRequest.getCategoryIdList().stream()
                    .map(this.categoryService::get)
                    .toList();
            saveBook.setCategoryList(categoryList);
        }

        this.bookService.save(saveBook);

        BookResponse bookResponse = this.modelMapper.forResponse().map(saveBook, BookResponse.class);
        return ResultHelper.createdData(bookResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);

        Author author = this.authorService.get(bookUpdateRequest.getAuthorId());
        updateBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookUpdateRequest.getPublisherId());
        updateBook.setPublisher(publisher);

        if (bookUpdateRequest.getCategoryIdList() != null && !bookUpdateRequest.getCategoryIdList().isEmpty()) {
            List<Category> categoryList = bookUpdateRequest.getCategoryIdList().stream()
                    .map(this.categoryService::get)
                    .toList();
            updateBook.setCategoryList(categoryList);
        }

        this.bookService.update(updateBook);

        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook, BookResponse.class);
        return ResultHelper.createdData(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") long id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.successData(bookResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<BookResponse>> getAll() {
        List<Book> bookList = this.bookService.getAll();
        List<BookResponse> bookResponseList = bookList.stream()
                .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class))
                .toList();

        return ResultHelper.successData(bookResponseList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") long id){
        this.bookService.delete(id);
        return ResultHelper.success();
    }

}
