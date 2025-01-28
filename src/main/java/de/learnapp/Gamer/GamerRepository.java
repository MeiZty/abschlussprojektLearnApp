package de.learnapp.Gamer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GamerRepository extends JpaRepository<GamerModel, Long> {
    Optional<GamerModel> findByLoginAndPassword(String login, String password);
    Optional<GamerModel> findFirstByLogin(String Login);
    GamerModel findByLogin(String username);
}
