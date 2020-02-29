package org.wcci.blog.StorageTests;

import org.junit.jupiter.api.Test;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.CategoryStorage;
import org.wcci.blog.Storage.CategoryStorageJpa;
import org.wcci.blog.Storage.Repositories.CategoryRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryStorageJpaTest {

    @Test
    public void ShouldFindAllCategories(){
        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
        Category testCategory = new Category("Testing My Patience");
        CategoryStorage underTest = new CategoryStorageJpa(mockCategoryRepository);
        when(mockCategoryRepository.findAll()).thenReturn(Collections.singletonList(testCategory));
        underTest.store(testCategory);
        verify(mockCategoryRepository).save(testCategory);
        assertThat(underTest.findAllCategories()).contains(testCategory);
    }

    @Test
    public void shouldRetrieveChosenCampusByName(){
        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
        Category testCategory1 = new Category("Test1");
        Category testCategory2 = new Category("Test2");
        CategoryStorage underTest = new CategoryStorageJpa(mockCategoryRepository);
        underTest.store(testCategory1);
        underTest.store(testCategory2);
        Optional<Category> testCategory1Optional = Optional.of(testCategory1);
        when(mockCategoryRepository.findByCategoryName("Test1")).thenReturn(testCategory1Optional);

        Optional<Category> testCategory2Optional = Optional.of(testCategory2);
        when(mockCategoryRepository.findByCategoryName("Test2")).thenReturn(testCategory2Optional);

        Category chosenCategory1 = underTest.findCategoryByName("Test1");
        Category chosenCategory2 = underTest.findCategoryByName("Test2");

        assertThat(chosenCategory1).isEqualTo(testCategory1);
        assertThat(chosenCategory2).isEqualTo(testCategory2);
    }
}
