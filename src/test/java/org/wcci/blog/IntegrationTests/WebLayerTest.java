package org.wcci.blog.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {
    @MockBean
    CategoryStorage mockCategoryStorage;

    @MockBean
    BlogStorage mockBlogStorage;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void categoriesShouldBeOkAndReturnListOfCategoriesWithModelAttribute() throws Exception {
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ListOfCategories"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void shouldReceiveOkFromSingleCategoryEndpoint() throws Exception {
        Category testCategory = new Category("Testcat");
        when(mockCategoryStorage.findCategoryByName("Testcat")).thenReturn(testCategory);
        mockMvc.perform(get("/categories/Testcat"))
                .andExpect(status().isOk())
                .andExpect(view().name("category-view"))
                .andExpect(model().attributeExists("categories"));
    }
}
