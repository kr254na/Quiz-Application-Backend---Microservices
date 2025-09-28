package com.krishna.question_service.repo;

import com.krishna.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer>
{
    List<Question> findByDomain(String category);
    @Query(value="Select q.id from question q where q.domain=:category order by rand() limit :numQ;",nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ")int numQ);
}