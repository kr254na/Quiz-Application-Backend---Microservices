package com.krishna.quiz_service.service;

import com.krishna.quiz_service.feign.QuizInterface;
import com.krishna.quiz_service.model.Question;
import com.krishna.quiz_service.model.QuestionWrapper;
import com.krishna.quiz_service.model.Quiz;
import com.krishna.quiz_service.model.Response;
import com.krishna.quiz_service.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        try
        {
            //call generate url - Rest Template
            //Eureka Server is used for service discovery
            //Feign Client provides declarative way of using other service
            //Prevent writing IP Address and Port Number using Feign
            List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ).getBody();
            Quiz quiz=new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            quizRepo.save(quiz);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to create the quiz",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id)
    {
        try
        {
            Quiz quiz=quizRepo.findById(id).get();
            List<Integer> questionIds=quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromId(questionIds);
            return questions;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {
        try
        {
            ResponseEntity<Integer> score=quizInterface.getScore(responses);
            return score;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
