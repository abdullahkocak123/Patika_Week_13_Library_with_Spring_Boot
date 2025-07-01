package com.patika.library.business.abstracts;

import com.patika.library.enitites.Author;

import java.util.List;

public interface IAuthorService {

    Author save (Author author);

    Author update (Author author);

    Author get(long id);

    List<Author> getAll();

    boolean delete (long id);
}
