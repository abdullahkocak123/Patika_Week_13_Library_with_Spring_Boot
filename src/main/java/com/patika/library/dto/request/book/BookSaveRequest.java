package com.patika.library.dto.request.book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotNull(message = "İsim boş olamaz.")
    private String name;

    private Integer publicationYear;

    @NotNull(message = "Stok boş olamaz.")
    private int stock;

    @NotNull(message = "Yazar ismi boş olamaz.")
    private Long authorId;

    @NotNull(message = "Yayıncı ismi boş olamaz.")
    private Long publisherId;

    @NotNull(message = "Kategori boş olamaz.")
    private List<Long> categoryIdList = new ArrayList<>();


}
