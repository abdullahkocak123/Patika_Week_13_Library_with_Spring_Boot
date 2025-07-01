package com.patika.library.business.concretes;

import com.patika.library.business.abstracts.IPublisherService;
import com.patika.library.core.exception.AlreadyExistsException;
import com.patika.library.core.exception.NotFoundException;
import com.patika.library.dao.PublisherRepo;
import com.patika.library.enitites.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RublisherManager implements IPublisherService {

    private final PublisherRepo publisherRepo;

    public RublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        if (this.publisherRepo.existsByName(publisher.getName())){
            throw new AlreadyExistsException();
        }
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        //check if there is a data, if not throw message
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(long id) {
        return this.publisherRepo.findById(id).orElseThrow(() ->  new NotFoundException(id));
    }

    @Override
    public List<Publisher> getAll() {
        return this.publisherRepo.findAll();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
