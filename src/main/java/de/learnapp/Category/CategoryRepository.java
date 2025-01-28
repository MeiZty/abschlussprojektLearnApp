package de.learnapp.Category;

import de.learnapp.QuestionsAnswers.QuestionsAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<QuestionsAnswers> findById(Category category);
}
