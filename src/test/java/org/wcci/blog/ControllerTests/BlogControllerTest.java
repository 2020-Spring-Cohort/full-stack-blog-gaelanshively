package org.wcci.blog.ControllerTests;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.Controllers.BlogController;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogControllerTest {

    private BlogController underTest;
    private Model model;
    private BlogStorage mockStorage;
    private Blog testBlog;

    @BeforeEach
    void setUp(){
        mockStorage = mock(BlogStorage.class);
        underTest = new BlogController(mockStorage);
        model = mock(Model.class);
        Category testCategory = new Category("Music");
        Blog testBlog = new Blog("Test", "Simply the test", "Tester than all the rest", testCategory);
        when(mockStorage.findBlogById(1L)).thenReturn(testBlog);
    }

    @Test
    public void displayBlogReturnsBlogTemplate(){
        String result = underTest.displayBlog(1L, model);
        assertThat(result).isEqualTo("blog-view");
    }

    @Test
    public void displayBlogMappingIsCorrect() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/blogs/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-view"))
                .andExpect(model().attributeExists("blog"))
                .andExpect(model().attribute("blogs", testBlog));
    }
}
