package org.wcci.blog.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.Controllers.CategoryController;
import org.wcci.blog.Models.Category;
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
    private CategoryStorage mockStorage;
    private Model mockModel;

    @BeforeEach
    public void setUp(){
        mockModel = mock(Model.class);
        mockStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOneCategory(){
        Category testCategory = new Category("Test");
        when(mockStorage.findCategoryByName("Test Posts")).thenReturn(testCategory);

        underTest.displayChosenCategory("Test Posts", mockModel);

        verify(mockStorage).findCategoryByName("Test Posts");
        verify(mockModel).addAttribute("category", testCategory);
    }

    @Test
    public void shouldReturnChosenCategoryWhenChosenCategoryIsCalled(){
        String viewName = underTest.displayChosenCategory("Test Posts", mockModel);
        assertThat(viewName).isEqualTo("chosenCategory");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception{
        Category testCategory = new Category("Test Category");
        when(mockStorage.findCategoryByName("Test Posts")).thenReturn(testCategory);

        mockMvc.perform(get("/categories/Test Posts"))
                .andExpect(status().isOk())
                .andExpect(view().name("chosenCategory"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", testCategory));
    }

    @Test
    public void categoriesEndPointShowsAllCategories() throws Exception {
        Category testCategory = new Category("Pet Stores");

        List<Category> categoryCollection = Collections.singletonList(testCategory);
        when(mockStorage.findAllCategories()).thenReturn(categoryCollection);
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categoriesView"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categoryCollection));
    }

    @Test
    public void addCategoryShouldRedirectToCategoryEndPoint(){
        String result = underTest.addCategory("Tests");
        assertThat(result).isEqualTo("redirect:categories");
    }

    @Test
    public void addCategoryShouldStoreTheCategory(){
        underTest.addCategory("Porkins");
        verify(mockStorage).store(new Category("Porkins"));
    }

    @Test
    public void addCategoryShouldAddNewCategoryEndpoint() throws Exception {
        mockMvc.perform(post("/add-category")
                            .param("categoryName", "CatIsPurring"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Category("CatIsPurring"));
    }
}
