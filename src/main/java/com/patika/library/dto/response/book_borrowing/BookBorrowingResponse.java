package com.patika.library.dto.response.book_borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {

    private Long id;

    private String borrowerName;
}
