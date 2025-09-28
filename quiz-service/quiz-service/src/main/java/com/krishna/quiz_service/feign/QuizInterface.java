package com.krishna.quiz_service.feign;

import com.krishna.quiz_service.model.QuestionWrapper;
import com.krishna.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface
{
    //generate Quiz
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam int numQuestions);
    //getQuestions(questionId) from Quiz Service
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId
    (@RequestBody List<Integer> questionIds);
    //getScore from Quiz Service
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore
    (@RequestBody List<Response> responses);
}
