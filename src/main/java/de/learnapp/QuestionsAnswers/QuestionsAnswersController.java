package de.learnapp.QuestionsAnswers;

import de.learnapp.Category.Category;
import de.learnapp.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class QuestionsAnswersController {
    @Autowired
    private final QuestionsAnswersRepository questionsAnswersRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    private Set<Long> usedQuestionIds = new HashSet<>();
    public QuestionsAnswersController(QuestionsAnswersRepository questionsAnswersRepository, CategoryRepository categoryRepository) {
        this.questionsAnswersRepository = questionsAnswersRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/questionsanswers/new")
    public String showNewQuestionsAnswersForm(Model model) {
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("category", new Category());
        model.addAttribute("questionsanswers", new QuestionsAnswers());
        model.addAttribute("listCategories", listCategories);
        return "questionsanswers_form";
    }
    @PostMapping("/questionsanswers/save")
    public String saveQuestionsAnswers(QuestionsAnswers questionsAnswers) {
        questionsAnswersRepository.save(questionsAnswers);
        return "redirect:/questionsanswers/new";
    }
    @GetMapping("/questionsanswers")
    public String listQuestionsAnswers(@RequestParam(name = "id", required = false) Long id,
                                       @RequestParam(name = "currentIndex", defaultValue = "0") int currentIndex,
                                       Model model) {
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("listCategories", listCategories);
        if (id != null) {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category != null) {
                List<QuestionsAnswers> questionsAnswersList = questionsAnswersRepository.findByCategory(category);
                List<QuestionsAnswers> unusedQuestions = questionsAnswersList
                        .stream()
                        .filter(qa -> !usedQuestionIds.contains(qa.getId()))
                        .toList();
                if (currentIndex < 0) {
                    currentIndex = 0;
                } else if (currentIndex >= unusedQuestions.size()) {
                    currentIndex = unusedQuestions.size() - 1;
                }
                if (!unusedQuestions.isEmpty()) {
                    model.addAttribute("questionsAnswersList", unusedQuestions);
                }
                usedQuestionIds.clear();
            }
        }
        model.addAttribute("selectedCategoryId", id);
        model.addAttribute("currentQuestionsAnswersIndex", currentIndex);
        return "questionsanswers";
    }
}