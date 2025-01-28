package de.learnapp.QuestionsAnswers;

import de.learnapp.Category.Category;
import de.learnapp.Category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class QuestionsAnswersRepositoryTest {

    @Autowired
    private QuestionsAnswersRepository questionsAnswersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category = entityManager.persistAndFlush(category);

        QuestionsAnswers qa1 = new QuestionsAnswers();
        qa1.setCategory(category);

        QuestionsAnswers qa2 = new QuestionsAnswers();
        qa2.setCategory(category);

        entityManager.persistAndFlush(qa1);
        entityManager.persistAndFlush(qa2);

        List<QuestionsAnswers> foundQAs = questionsAnswersRepository.findByCategory(category);

        assertEquals(2, foundQAs.size());
    }
}
