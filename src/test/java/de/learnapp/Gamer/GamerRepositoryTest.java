package de.learnapp.Gamer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GamerRepositoryTest {

    @Autowired
    private GamerRepository gamerRepository;

    @Test
    public void testFindByLoginAndPassword() {
        GamerModel gamer = new GamerModel();
        gamer.setLogin("testuser");
        gamer.setPassword("password123");
        gamerRepository.save(gamer);
        Optional<GamerModel> foundGamer = gamerRepository.findByLoginAndPassword("testuser", "password123");
        assertTrue(foundGamer.isPresent());
        assertEquals("testuser", foundGamer.get().getLogin());
        assertEquals("password123", foundGamer.get().getPassword());
    }

    @Test
    public void testFindFirstByLogin() {
        GamerModel gamer = new GamerModel();
        gamer.setLogin("testuser");
        gamerRepository.save(gamer);
        Optional<GamerModel> foundGamer = gamerRepository.findFirstByLogin("testuser");
        assertTrue(foundGamer.isPresent());
        assertEquals("testuser", foundGamer.get().getLogin());
    }

    @Test
    public void testFindByLogin() {
        GamerModel gamer = new GamerModel();
        gamer.setLogin("testuser");
        gamerRepository.save(gamer);
        GamerModel foundGamer = gamerRepository.findByLogin("testuser");
        assertEquals("testuser", foundGamer.getLogin());
    }

    @Test
    public void testFindByLoginNonExistent() {
        GamerModel foundGamer = gamerRepository.findByLogin("nonexistentuser");
        assertNull(foundGamer);
    }
}
