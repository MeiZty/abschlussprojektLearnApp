package de.learnapp.Gamer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamerControllerTest {
    private GamerController gamerController;
    @Mock
    private GamerService gamerService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gamerController = new GamerController(gamerService);
    }
    @Test
    public void testGetRegister() {
        Model model = new RedirectAttributesModelMap();
        String viewName = gamerController.getRegister(model);
        assertEquals("register", viewName);
    }
    @Test
    public void testGetLogin() {
        Model model = new RedirectAttributesModelMap();
        String viewName = gamerController.getLogin(model);
        assertEquals("login", viewName);
    }
    @Test
    public void testRegister() {
        GamerModel gamerModel = new GamerModel();
        gamerModel.setLogin("testuser");
        gamerModel.setPassword("testpassword");
        gamerModel.setEmail("test@example.com");
        Mockito.when(gamerService.registerGamer(gamerModel.getLogin(), gamerModel.getPassword(), gamerModel.getEmail()))
                .thenReturn(gamerModel);
        String viewName = gamerController.register(gamerModel);
        assertEquals("redirect:/login", viewName);
    }
    @Test
    public void testLogin() {
        GamerModel gamerModel = new GamerModel();
        gamerModel.setLogin("testuser");
        gamerModel.setPassword("testpassword");
        Mockito.when(gamerService.authenticate(gamerModel.getLogin(), gamerModel.getPassword()))
                .thenReturn(gamerModel);
        Model model = new RedirectAttributesModelMap();
        String viewName = gamerController.login(gamerModel, model);
        assertEquals("redirect:/questionsanswers/new", viewName);
        assertEquals(gamerModel.getLogin(), model.getAttribute("gamerLogin"));
    }
}
