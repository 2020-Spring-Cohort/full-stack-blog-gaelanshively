package org.wcci.blog.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.Repositories.BlogRepository;
import org.wcci.blog.Storage.Repositories.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void aCategoryShouldShowRelevantPosts(){
        Category testCategory = new Category("Tests");

        Blog testBlog = new Blog("Good", "Tests pass", "TestMaster", testCategory);
        categoryRepository.save(testCategory);
        blogRepository.save(testBlog);

        testEntityManager.flush();
        testEntityManager.clear();

        Optional<Category> retrievedCategoryOptional = categoryRepository.findById(testCategory.getId());
        Category retrievedCategory = retrievedCategoryOptional.get();
        Blog retrievedBlog = blogRepository.findById(testBlog.getId()).get();

        assertThat(retrievedCategory.getBlogs()).contains(testBlog);
    }
}
