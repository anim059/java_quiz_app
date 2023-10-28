package com.springCRUD.app.springcrudapp.DAO;

import com.springCRUD.app.springcrudapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);


    @Query(value = "SELECT * FROM question q Where q.category = :category ORDER BY RAND() LIMIT :size", nativeQuery = true)
    List<Question> createRandomQuizQuestion(@Param("category") String category, @Param("size") Integer size);
}
