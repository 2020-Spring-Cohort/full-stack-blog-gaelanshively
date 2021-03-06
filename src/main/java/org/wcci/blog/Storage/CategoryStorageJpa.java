package org.wcci.blog.Storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.Repositories.CategoryRepository;

import java.util.Collection;

@Service
public class CategoryStorageJpa implements CategoryStorage{
    private CategoryRepository categoryRepository;

    public CategoryStorageJpa(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> findAllCategories(){
        return (Collection<Category>) categoryRepository.findAll();
    }

    @Override
    public void store(Category category){
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).get();
    }
}
