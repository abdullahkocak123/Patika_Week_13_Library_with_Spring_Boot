package com.patika.library.business.concretes;

import com.patika.library.business.abstracts.IAuthorService;
import com.patika.library.core.exception.AlreadyExistsException;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.dao.AuthorRepo;
import com.patika.library.enitites.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        if (this.authorRepo.existsByName(author.getName())){
            throw new AlreadyExistsException();
        }
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        //check if there is a data, if not throw message
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(long id) {
        return this.authorRepo.findById(id).orElseThrow(() ->  new NotFoundException(id));
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepo.findAll();
    }

    @Override
    public boolean delete(long id) {
        //check if there is a data, if not throw message
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}
