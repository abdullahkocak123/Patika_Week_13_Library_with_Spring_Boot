package com.patika.library.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_borrowing_id")
    private Long id;

    @Column(name = "borrower_name", length = 100, nullable = false)
    private String borrowerName;

    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrowing_book_id", referencedColumnName = "book_id")
    private Book book;



    @Override
    public String toString() {
        return "BookBorrowing{" +
                "id=" + id +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", bookName=" + book.getName() +
                '}';
    }
}

