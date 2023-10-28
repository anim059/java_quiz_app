package com.springCRUD.app.springcrudapp.DAO;

import com.springCRUD.app.springcrudapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
