package com.springCRUD.app.springcrudapp.service;

import com.springCRUD.app.springcrudapp.DAO.QuestionDAO;
import com.springCRUD.app.springcrudapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>>  getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDAO.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question, int id) {
        try {
            Optional<Question> questionData = questionDAO.findById(id);
            if (questionData.isEmpty()) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            Question getQuestionData = questionData.get();
            getQuestionData.setId(id);
            getQuestionData.setCategory(question.getCategory());
            getQuestionData.setQuestion_title(question.getQuestion_title());
            getQuestionData.setOption1(question.getOption1());
            getQuestionData.setOption2(question.getOption2());
            getQuestionData.setOption3(question.getOption3());
            getQuestionData.setOption4(question.getOption4());
            getQuestionData.setDifficultyLevel(question.getDifficultyLevel());
            getQuestionData.setRight_ans(question.getRight_ans());
            questionDAO.save(getQuestionData);
            return new ResponseEntity<>("UPDATED", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try{
            Optional<Question> questionData = questionDAO.findById(id);
            if(questionData.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            questionDAO.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
