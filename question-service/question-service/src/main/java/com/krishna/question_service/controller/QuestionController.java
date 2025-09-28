package com.krishna.question_service.controller;

import com.krishna.question_service.model.Question;
import com.krishna.question_service.model.QuestionWrapper;
import com.krishna.question_service.model.Response;
import com.krishna.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController
{
    @Autowired
    QuestionService service;
    @Autowired
    Environment environment;
    @GetMapping("all")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return service.getAllQuestions();
    }
    @GetMapping("domain/{domain}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String domain)
    {
        return service.getQuestionsByDomain(domain);
    }
    @PostMapping("new")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        return service.addQuestion(question);
    }
    @PutMapping("update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
    {
        return service.addQuestion(question);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Question>> deleteQuestion(@PathVariable int id)
    {
        return service.deleteQuestion(id);
    }
    //Generate Quiz Request from Quiz Service
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName,@RequestParam int numQuestions)
    {
        return service.getQuestionsForQuiz(categoryName,numQuestions);
    }
    //getQuestions(questionId) from Quiz Service
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return service.getQuestionsForId(questionIds);
    }
    //getScore from Quiz Service
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
       return service.getScore(responses);
    }
}
