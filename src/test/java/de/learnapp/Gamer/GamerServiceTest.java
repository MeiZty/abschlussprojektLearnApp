package de.learnapp.Gamer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GamerServiceTest {

    @Mock
    private GamerRepository gamerRepository;

    private GamerService gamerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gamerService = new GamerService(gamerRepository);
    }

    @Test
    public void testAuthenticate() {
        GamerModel mockGamer = new GamerModel();
        mockGamer.setLogin("testuser");
        mockGamer.setPassword("password123");
        when(gamerRepository.findByLoginAndPassword("testuser", "password123")).thenReturn(Optional.of(mockGamer));
        GamerModel authenticatedGamer = gamerService.authenticate("testuser", "password123");
        assertEquals(mockGamer, authenticatedGamer);
    }
}
