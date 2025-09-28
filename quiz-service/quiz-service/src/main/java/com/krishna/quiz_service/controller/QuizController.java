package com.krishna.quiz_service.controller;

import com.krishna.quiz_service.model.QuestionWrapper;
import com.krishna.quiz_service.model.Quiz;
import com.krishna.quiz_service.model.QuizDto;
import com.krishna.quiz_service.model.Response;
import com.krishna.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("quiz")
public class QuizController
{
    @Autowired
    private QuizService service;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return service.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id)
    {
        return service.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response)
    {
        return service.calculateResult(id,response);
    }
}
