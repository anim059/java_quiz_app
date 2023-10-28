package com.springCRUD.app.springcrudapp.controller;

import com.springCRUD.app.springcrudapp.model.Question;
import com.springCRUD.app.springcrudapp.model.QuestionWrapper;
import com.springCRUD.app.springcrudapp.model.UserSubmittedAnswer;
import com.springCRUD.app.springcrudapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam Integer size){
        return quizService.createQuizQuestion(category, title, size);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<String> getQuizResult(@PathVariable Integer id, @RequestBody List<UserSubmittedAnswer> response){
        return quizService.getQuizResult(id, response);
    }
}
