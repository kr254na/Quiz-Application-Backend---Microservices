package com.krishna.question_service.service;

import com.krishna.question_service.model.Question;
import com.krishna.question_service.model.QuestionWrapper;
import com.krishna.question_service.model.Response;
import com.krishna.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
    @Autowired
    private QuestionRepo repo;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByDomain(String domain)
    {
        try
        {
            return new ResponseEntity<>(repo.findByDomain(domain),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Question> addQuestion(Question question)
    {
        try {
            return new ResponseEntity<>(repo.save(question), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Optional<Question>> deleteQuestion(int id)
    {
        try {
            Optional<Question> question = repo.findById(id);
            question.ifPresent(q -> repo.deleteById(id));
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(Optional.empty(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (String categoryName, int numQuestions)
    {
        List<Integer> questions=repo.findRandomQuestionByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForId(List<Integer> questionIds)
    {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id:questionIds)
        {
            questions.add(repo.findById(id).get());
        }
        for(Question question:questions)
        {
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion(question.getQuestion());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses)
    {
       int right=0;
        for(Response response:responses)
        {
            Question question=repo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
