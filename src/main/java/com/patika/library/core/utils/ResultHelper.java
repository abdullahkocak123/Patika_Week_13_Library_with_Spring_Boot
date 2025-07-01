package com.patika.library.core.utils;

import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;

public class ResultHelper {

    public static Result notFoundException (String msg){
        return new Result(false, msg, "404");
    }

    public static <T>ResultData<T> createdData(T data){
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static Result alreadyExistsException (String msg){
        return new Result(false, Msg.ALREADY_EXISTS, "409");
    }

    public static <T>ResultData<T> successData(T data){
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static Result success(){
        return new Result(true,Msg.OK,"200");
    }

    public static Result bookExistsWithCategory(String msg){
        return new Result(false,Msg.BOOK_EXISTS_BY_CATEGORY,"409");
    }

    public static Result notEnoughStock(String msg) {
        return new Result(false, Msg.NOT_ENOUGH_STOCK, "404");
    }
}
