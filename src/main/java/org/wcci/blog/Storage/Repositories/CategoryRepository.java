package org.wcci.blog.Storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
