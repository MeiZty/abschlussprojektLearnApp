package de.learnapp.QuestionsAnswers;

import de.learnapp.Category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;

public class QuestionsAnswersControllerTest {

    @InjectMocks
    private QuestionsAnswersController questionsAnswersController;

    @Mock
    private QuestionsAnswersRepository questionsAnswersRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveQuestionsAnswers() {
        QuestionsAnswers questionsAnswers = new QuestionsAnswers();
        when(questionsAnswersRepository.save(questionsAnswers)).thenReturn(questionsAnswers);

        String viewName = questionsAnswersController.saveQuestionsAnswers(questionsAnswers);

        assert("redirect:/questionsanswers/new".equals(viewName));
    }
}
