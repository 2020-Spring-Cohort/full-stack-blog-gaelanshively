package org.wcci.blog.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.Controllers.CategoryController;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {

    private MockMvc mockMvc;
    private CategoryController underTest;
    private CategoryStorage categoryStorage;
    private BlogStorage blogStorage;
    private Model mockModel;

    @BeforeEach
    public void setUp(){
        mockModel = mock(Model.class);
        categoryStorage = mock(CategoryStorage.class);
        blogStorage = mock(BlogStorage.class);
        underTest = new CategoryController(categoryStorage, blogStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneCategory(){
        Category testCategory = new Category("Test");
        when(categoryStorage.findCategoryByName("TestPosts")).thenReturn(testCategory);

        underTest.displayChosenCategory("TestPosts", mockModel);

        verify(categoryStorage).findCategoryByName("TestPosts");
        verify(mockModel).addAttribute("categories", testCategory);
    }

    @Test
    public void shouldReturnChosenCategoryWhenChosenCategoryIsCalled(){
        String viewName = underTest.displayChosenCategory("TestPosts", mockModel);
        assertThat(viewName).isEqualTo("category-view");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception{
        Category testCategory = new Category("TestCategory");
        when(categoryStorage.findCategoryByName("TestPosts")).thenReturn(testCategory);

        mockMvc.perform(get("/categories/TestPosts"))
                .andExpect(status().isOk())
                .andExpect(view().name("category-view"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", testCategory));
    }

    @Test
    public void categoriesEndPointShowsAllCategories() throws Exception {
        Category testCategory = new Category("PetStores");

        List<Category> categoryCollection = Collections.singletonList(testCategory);
        when(categoryStorage.findAllCategories()).thenReturn(categoryCollection);
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfCategories"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categoryCollection));
    }

    @Test
    public void addCategoryShouldRedirectToCategoryEndPoint(){
        String result = underTest.addCategory("Tests");
        assertThat(result).isEqualTo("redirect:ListOfCategories");
    }

    @Test
    public void addCategoryShouldStoreTheCategory(){
        underTest.addCategory("Porkins");
        verify(categoryStorage).store(new Category("Porkins"));
    }

    @Test
    public void addCategoryShouldAddNewCategoryEndpoint() throws Exception {
        mockMvc.perform(post("/add-category")
                            .param("categoryName", "CatIsPurring"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(categoryStorage).store(new Category("CatIsPurring"));
    }
}
