package com.springCRUD.app.springcrudapp.service;

import com.springCRUD.app.springcrudapp.DAO.QuestionDAO;
import com.springCRUD.app.springcrudapp.DAO.QuizDAO;
import com.springCRUD.app.springcrudapp.model.Question;
import com.springCRUD.app.springcrudapp.model.QuestionWrapper;
import com.springCRUD.app.springcrudapp.model.Quiz;
import com.springCRUD.app.springcrudapp.model.UserSubmittedAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuizQuestion(String category, String title, Integer size) {
        try{
            List<Question> questions = questionDAO.findAll();
            if(questions.size()+1 < size){
                return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
            }
            List<Question> randomQuestions = questionDAO.createRandomQuizQuestion(category, size);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionList(randomQuestions);
            quizDAO.save(quiz);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        try{
            Optional<Quiz> quiz = quizDAO.findById(id);
            List<Question> questionList = quiz.get().getQuestionList();
            List<QuestionWrapper> finalQues = new ArrayList<QuestionWrapper>();
            for(Question q : questionList){
                QuestionWrapper quizQ = new QuestionWrapper(q.getId(), q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                finalQues.add(quizQ);
            }
            return new ResponseEntity<>(finalQues, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getQuizResult(Integer id, List<UserSubmittedAnswer> response) {
        try{
            Optional<Quiz> quiz = quizDAO.findById(id);
            List<Question> questionList = quiz.get().getQuestionList();
            Collections.sort(questionList, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    return o1.getId() - o2.getId();
                }
            });
            Collections.sort(response, new Comparator<UserSubmittedAnswer>() {
                @Override
                public int compare(UserSubmittedAnswer o1, UserSubmittedAnswer o2) {
                    return o1.getId() - o2.getId();
                }
            });
            Integer i=0;
            Integer rightAns = 0;
            for(UserSubmittedAnswer ua : response){
                if(ua.getAnswer().equals(questionList.get(i).getRight_ans())){
                    rightAns++;
                }
                i++;
            }
            return new ResponseEntity<>(rightAns.toString(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}

