package com.patika.library.dao;

import com.patika.library.enitites.Author;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    boolean existsByName(@NotNull String name);
}
