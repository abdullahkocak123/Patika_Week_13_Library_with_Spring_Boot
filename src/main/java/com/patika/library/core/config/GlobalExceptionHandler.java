package com.patika.library.core.config;

import com.patika.library.core.exception.AlreadyExistsException;
import com.patika.library.core.exception.BookExistsWithCategoryException;
import com.patika.library.core.exception.NotEnoughStockException;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.core.result.Result;
import com.patika.library.core.utils.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException (NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundException(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Result> handleAlreadyExistsException (AlreadyExistsException e){
        return new ResponseEntity<>(ResultHelper.alreadyExistsException(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookExistsWithCategoryException.class)
    public ResponseEntity<Result> handleBookExistsWithCategoryException (BookExistsWithCategoryException e){
        return new ResponseEntity<>(ResultHelper.bookExistsWithCategory(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<Result> handleNotEnoughStockException (NotEnoughStockException e){
        return new ResponseEntity<>(ResultHelper.notEnoughStock(e.getMessage()), HttpStatus.CONFLICT);
    }
}
