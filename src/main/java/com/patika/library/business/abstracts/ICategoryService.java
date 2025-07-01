package com.patika.library.business.abstracts;

import com.patika.library.enitites.Category;

import java.util.List;

public interface ICategoryService {

    Category save (Category category);

    Category update (Category category);

    Category get(long id);

    List<Category> getAll();

    boolean delete (long id);
}
