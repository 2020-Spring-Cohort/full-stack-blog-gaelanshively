package org.wcci.blog.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryStorage categoryStorage;

    @MockBean
    BlogStorage blogStorage;

    @Test
    public void categoriesEndPointShouldReturnOk() throws Exception {
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void eachCategoryEndPointShouldReturnOk() throws Exception {
        Category testCategory = new Category("AnotherTest");
        when(categoryStorage.findCategoryByName("AnotherTest")).thenReturn(testCategory);
        mockMvc.perform(get("/categories/AnotherTest"))
                .andExpect(status().isOk());
    }
}
