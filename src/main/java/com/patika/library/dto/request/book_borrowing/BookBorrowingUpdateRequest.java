package com.patika.library.dto.request.book_borrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {

    @NotNull(message = "Id değeri null olamaz")
    @Positive(message = "Id değeri pozitif değer olmak zorunda")
    private Long id;

    @NotNull(message = "Ödünç alan ismi boş olamaz.")
    private String borrowerName;

    private LocalDate borrowingDate;

    private LocalDate returnDate;

    @NotNull(message = "Kitap ismi boş olamaz.")
    private Long bookId;
}
