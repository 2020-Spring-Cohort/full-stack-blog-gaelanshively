package org.wcci.blog.IntegrationTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

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

}
