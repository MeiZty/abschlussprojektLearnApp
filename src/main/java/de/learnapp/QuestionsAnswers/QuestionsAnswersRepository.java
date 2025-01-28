package de.learnapp.QuestionsAnswers;

import de.learnapp.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsAnswersRepository extends JpaRepository<QuestionsAnswers, Long> {
    List<QuestionsAnswers> findByCategory(Category category);
}