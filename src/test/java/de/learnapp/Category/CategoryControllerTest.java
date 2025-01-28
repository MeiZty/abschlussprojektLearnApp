package de.learnapp.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private CategoryRepository categoryRepository;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryRepository)).build();
    }
    @Test
    public void testShowCategoryNewForm() throws Exception {
        ResultActions result = mockMvc.perform(get("/categories/new"));

        result.andExpect(status().isOk())
                .andExpect(view().name("category_form"))
                .andExpect(model().attributeExists("category"));
    }
}
