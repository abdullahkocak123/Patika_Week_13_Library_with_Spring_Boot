package com.patika.library.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    @Column(name = "publisher_name", length =100, nullable = false)
    private String name;

    @Column(name = "publisher_establishment_year")
    private Integer establishmentYear;

    @Column(name = "publisher_address")
    private String address;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();


    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", establishmentYear=" + establishmentYear +
                ", address='" + address + '\'' +
                '}';
    }
}

