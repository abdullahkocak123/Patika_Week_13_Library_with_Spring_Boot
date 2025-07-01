package com.patika.library.dao;

import com.patika.library.enitites.Publisher;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {

    boolean existsByName(@NotNull String name);
}
