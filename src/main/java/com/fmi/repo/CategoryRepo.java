package com.fmi.repo;

import com.fmi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findOneByName(String name);
}
