package com.patika.library.business.concretes;

import com.patika.library.business.abstracts.ICategoryService;
import com.patika.library.core.exception.AlreadyExistsException;
import com.patika.library.core.exception.BookExistsWithCategoryException;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.dao.CategoryRepo;
import com.patika.library.enitites.Author;
import com.patika.library.enitites.Book;
import com.patika.library.enitites.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        if (this.categoryRepo.existsByName(category.getName())){
            throw new AlreadyExistsException();
        }
        return this.categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        //check if there is a data, if not throw message
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(long id) {
        return this.categoryRepo.findById(id).orElseThrow(() ->  new NotFoundException(id));
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public boolean delete(long id) {
        //check if there is a data, if not throw message
        Category category = this.get(id);

        List<Book> bookList = category.getBookList(); // kategoriye ait kitaplar çekildi

        if (bookList != null && !bookList.isEmpty()) {
            throw new BookExistsWithCategoryException();
        }

        this.categoryRepo.delete(category);
        return true;
    }
}
