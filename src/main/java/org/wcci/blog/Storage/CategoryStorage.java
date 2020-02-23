package org.wcci.blog.Storage;

import org.wcci.blog.Models.Category;

import java.util.Collection;

public interface CategoryStorage {
    Collection<Category> findAllCategories();

    void store(Category category);

    Category findCategoryByName(String categoryName);
}
