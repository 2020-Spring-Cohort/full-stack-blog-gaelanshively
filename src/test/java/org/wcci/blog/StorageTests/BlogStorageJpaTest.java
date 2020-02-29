package org.wcci.blog.StorageTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.BlogStorageJpa;
import org.wcci.blog.Storage.Repositories.BlogRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BlogStorageJpaTest {
    private BlogRepository blogRepository;
    private BlogStorage underTest;
    private Blog testBlog;

    @BeforeEach
    void setUp(){
        blogRepository = mock(BlogRepository.class);
        underTest = new BlogStorageJpa(blogRepository);
        Category testCategory = new Category("Testing");
        testBlog = new Blog("Testblog", "Good tests, right?", "MasterTester", testCategory);
    }

    @Test
    public void shouldFindBlogsById(){
        when(blogRepository.findById(1L)).thenReturn(Optional.of(testBlog));
        Blog chosenBlog = underTest.findBlogById(1L);
        assertThat(chosenBlog).isEqualTo(testBlog);
    }

    @Test
    public void storeBlogShouldStoreBlog() {
        underTest.store(testBlog);
        verify(blogRepository).save(testBlog);
    }
}
