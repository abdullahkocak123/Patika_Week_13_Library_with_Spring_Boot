package com.patika.library.dao;

import com.patika.library.enitites.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    boolean existsByName(@NotNull String name);
}
