package org.wcci.blog.ControllerTests;

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
import org.wcci.blog.Storage.CategoryStorage;
import org.wcci.blog.Storage.Repositories.TagRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogControllerTest {

    private BlogController underTest;
    private Model model;
    private BlogStorage blogStorage;
    private CategoryStorage categoryStorage;
    private TagRepository tagRepository;
    private Blog testBlog;

    @BeforeEach
    void setUp(){
        blogStorage = mock(BlogStorage.class);
        categoryStorage = mock(CategoryStorage.class);
        tagRepository = mock(TagRepository.class);
        underTest = new BlogController(blogStorage, categoryStorage, tagRepository);
        model = mock(Model.class);
        Category testCategory = new Category("Music");
        Blog testBlog = new Blog("Test", "Simply the test", "Tester than all the rest", testCategory);
        when(blogStorage.findBlogById(1L)).thenReturn(testBlog);
    }

    @Test
    public void displayBlogReturnsBlogTemplate(){
        String result = underTest.displayBlog(1L, model);
        assertThat(result).isEqualTo("blog-view");
    }


    //Test fails and I can't figure out why.
    // says it expects null but it returns testBlog, but it shouldn't expect null
    // testBlog is exactly what I want to see so I don't know how to fix this
    @Test
    public void displayBlogMappingIsCorrect() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-view"))
                .andExpect(model().attributeExists("blog"))
                .andExpect(model().attribute("blog", testBlog));
    }
}
