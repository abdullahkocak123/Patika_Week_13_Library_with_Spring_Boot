package com.patika.library.enitites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "author_name", length = 100, nullable = false)
    private String name;

    @Column(name = "author_birth_date")
    private Integer birthDate;

    @Column(name = "author_country")
    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;

    public Author() {
    }



    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                '}';
    }
}
