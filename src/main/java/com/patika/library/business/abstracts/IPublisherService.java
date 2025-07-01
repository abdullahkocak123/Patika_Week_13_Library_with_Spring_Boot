package com.patika.library.business.abstracts;

import com.patika.library.enitites.Publisher;

import java.util.List;

public interface IPublisherService {

    Publisher save (Publisher publisher);

    Publisher update (Publisher publisher);

    Publisher get(long id);

    List<Publisher> getAll();

    boolean delete (long id);
}
